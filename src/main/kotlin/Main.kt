import model.Team
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import org.jfree.chart.labels.StandardPieSectionLabelGenerator
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import parser.CsvParser
import resolver.Resolver
import java.io.File

fun main(args: Array<String>) {
    val players = CsvParser.readCsvWithPlayers("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)
    val (bestScoreDefender, bestDefenderScore) = resolver.getBestScorerDefender()

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников: $bestScoreDefender, их количество: $bestDefenderScore")
    println("Название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим числом удалений на одного игрока: ${resolver.getTheRudestTeam().name}")

    visualize(resolver.getStatistic())
}

fun visualize(data: List<Pair<Team, Int>>) {
    val dataset = DefaultPieDataset<String>()
    data.forEach {dataset.setValue(it.first.name, it.second)}
    val chart = ChartFactory.createPieChart(
        "Топ-10 команд с наивысшей суммарной трансферной стоимостью",
        dataset
    )
    (chart.plot as PiePlot<*>).labelGenerator = StandardPieSectionLabelGenerator("{1}")

    ChartUtils.saveChartAsPNG(File("src/main/chart.png"), chart, 800, 600)
}