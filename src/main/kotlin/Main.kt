import model.Position
import parser.CsvParser
import resolver.Resolver
import org.jfree.chart.ChartFactory
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.CategoryDataset
import org.jfree.chart.ChartPanel
import org.jfree.chart.ChartUtils
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.category.DefaultCategoryDataset
import java.awt.Dimension
import java.io.File

val translate = mapOf( "DEFENDER" to "ЗАЩИТНИК", "FORWARD" to "НАПАДАЮЩИЙ", "MIDFIELD" to "ПОЛУЗАЩИТНИК", "GOALKEEPPER" to "ВРАТАРЬ")

fun main(args: Array<String>) {
    // Получаем всех игроков из csv файла и записваем их в Resolver
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    // Кол-во игроков без агенства
    println(resolver.getCountWithoutAgency())

    // Автор наибольшего числа голов из числа защитников и кол-во голов
    println(resolver.getBestScorerDefender())

    // Русское название позиции самого дорогого немецкого игрока
    println(translate[resolver.getTheExpensiveGermanPlayerPosition()])

    // Команда с наибольшим средним числом красных карточек на игрока
    println(resolver.getTheRudestTeam().name)

    /*-----------------------------------------Вариант 3-----------------------------------------*/
    /*                    Зависимость кол-ва забитых голов от трансферной стоимости              */
    /*-------------------------------------------------------------------------------------------*/

    var goalsMap: MutableMap<Int, Pair<Int, Int>> = mapOf<Int, Pair<Int, Int>>().toMutableMap()
    players.forEach {
        val tup: Pair<Int, Int> = goalsMap.getOrDefault(it.goalsCount, Pair<Int, Int>(0, 0));
        goalsMap[it.goalsCount] = Pair<Int, Int>(tup.first + 1, tup.second + it.transferCost)
    }
    goalsMap = goalsMap.toSortedMap()

    val dataset = DefaultCategoryDataset()
    goalsMap.forEach {
        val goal = it.key
        dataset.addValue(it.value.second / it.value.first,  goal, "Goal")
    }

    val chart = ChartFactory.createBarChart(
        "Зависимость кол-ва забитых голов от трансферной стоимости",
        "Кол-во голов",
        "Трансферная стоимость",
        dataset,
        PlotOrientation.VERTICAL,
        true,
        false,
        false
    )

    ChartUtils.saveChartAsPNG(File("/chart.png"), chart, 1200, 600)
    val frame = ApplicationFrame("smt")
    frame.contentPane = ChartPanel(chart). apply { preferredSize = Dimension(1200, 600) }
    frame.pack()
    frame.isVisible = true

}