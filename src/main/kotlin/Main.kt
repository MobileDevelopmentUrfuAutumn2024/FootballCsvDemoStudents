import model.Team
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import org.jfree.chart.labels.StandardPieSectionLabelGenerator
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import parser.CsvParser
import resolver.Resolver
import java.io.File

fun main() {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)
    val chartData = resolver.getTopTenTransferCostTeams()

    val countPlayersWithoutAgency = resolver.getCountWithoutAgency()
    val bestScorerDefender = resolver.getBestScorerDefender()
    val expensiveGermanPlayerPosition = resolver.getTheExpensiveGermanPlayerPosition()
    val rudestTeam = resolver.getTheRudestTeam().name

    println("Количество игроков, интересы которых не представляет агенство: $countPlayersWithoutAgency")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${bestScorerDefender.first}, ${bestScorerDefender.second} голов")
    println("Русское название позиции самого дорогого немецкого игрока: $expensiveGermanPlayerPosition")
    println("Команда с наибольшим числом красных карточек на одного игрока: $rudestTeam")

    showChart(chartData)
}

fun showChart(data: List<Pair<Team, Int>>) {
    val pieDataset = DefaultPieDataset<String>()

    data.forEach { (team, totalCost) ->
        pieDataset.setValue(team.name, totalCost)
    }

    val pieChart = ChartFactory.createPieChart(
        "Топ-10 команд с наивысшей суммарной трансферной стоимостью", pieDataset
    )

    (pieChart.plot as PiePlot<*>).labelGenerator =
        StandardPieSectionLabelGenerator("Команда: {0}, Стоимость: {1}")

    ChartUtils.saveChartAsPNG(
        File("src/main/Most expensive teams.png"), pieChart, 1000, 1000
    )
}