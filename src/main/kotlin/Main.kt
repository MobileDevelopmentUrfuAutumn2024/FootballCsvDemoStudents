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

fun main(args: Array<String>) {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver: IResolver = Resolver(players)

    val countPlayersWithoutAgency: Int = resolver.getCountWithoutAgency()
    val authorGoalsAndCount: Pair<String, Int> = resolver.getBestScorerDefender()
    val nameGermanyPlayer: String = resolver.getTheExpensiveGermanPlayerPosition()
    val countDeleteForOnePlayer: Team = resolver.getTheRudestTeam()

    println("Количество игроков, интересы которых не представляет агенство: $countPlayersWithoutAgency")
    println("Автор наибольшего числа голов из числа защитников и их количество: $authorGoalsAndCount")
    println("Русское название позиции самого дорогого немецкого игрока: $nameGermanyPlayer")
    println("Команда с наибольшим числом удалений на одного игрока: $countDeleteForOnePlayer")

    val positionDistribution = resolver.getProportionsOfPlayers()
    val chart = createPieChart(positionDistribution)
    showChart(chart)
}



fun createPieChart(positionDistribution: Map<String, Int>): JFreeChart {
    val dataset = DefaultPieDataset<String>()
    positionDistribution.forEach { (position, count) ->
        dataset.setValue(position, count.toDouble())
    }

    val chart = ChartFactory.createPieChart(
        "Распределение игроков по позициям",
        dataset,
        true, true, false
    )
    val plot = chart.plot as PiePlot<*>
    val labelGenerator: PieSectionLabelGenerator = StandardPieSectionLabelGenerator("{0}: {1} ({2})")
    plot.labelGenerator = labelGenerator
    return chart
}

fun showChart(chart: JFreeChart) {
    val frame = JFrame("Круговая диаграмма")
    val chartPanel = ChartPanel(chart)
    frame.contentPane.add(chartPanel, BorderLayout.CENTER)
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
}
