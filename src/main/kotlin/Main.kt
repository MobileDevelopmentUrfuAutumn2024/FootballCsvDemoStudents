import model.Team
import parser.CsvParser
import resolver.IResolver
import resolver.Resolver
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.labels.PieSectionLabelGenerator
import org.jfree.chart.labels.StandardPieSectionLabelGenerator
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.WindowConstants

fun generatePieChart(positionDistribution: Map<String, Int>): JFreeChart {

    val data = DefaultPieDataset<String>()
    positionDistribution.entries.forEach { entry ->
        data.setValue(entry.key, entry.value.toDouble())
    }

    val chart = ChartFactory.createPieChart(
        "Разделение игроков по позициям",
        data,
        true, true, false
    )

    val plot = chart.plot as PiePlot<*>
    val labelGenerator: PieSectionLabelGenerator = StandardPieSectionLabelGenerator("{0}: {1} ({2})")
    plot.labelGenerator = labelGenerator

    return chart
}

fun showChart(chart: JFreeChart) {

    val frame = JFrame("Круговая диаграмма по позициям")
    val chartPanel = ChartPanel(chart)
    frame.contentPane.add(chartPanel, BorderLayout.CENTER)
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
}

fun main(args: Array<String>) {

    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver: IResolver = Resolver(players)
    val positionDivision = resolver.getPositionDivision()
    val chart = generatePieChart(positionDivision)
    val playersWithoutAgency: Int = resolver.getCountWithoutAgency()
    val bestDefenderScorer: Pair<String, Int> = resolver.getBestScorerDefender()
    val expensiveGermanyPlayerPosition: String = resolver.getTheExpensiveGermanPlayerPosition()
    val rudestTeam: Team = resolver.getTheRudestTeam()

    showChart(chart)

    println("Количество игроков, интересы которых не представляет агентство: $playersWithoutAgency")
    println("Автор наибольшего числа голов из числа защитников и их количество: $bestDefenderScorer")
    println("Русское название позиции самого дорогого немецкого игрока: $expensiveGermanyPlayerPosition")
    println("Команда с наибольшим средним числом красных карточек на одного игрока: $rudestTeam")
}