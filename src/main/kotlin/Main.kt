import model.Team
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import org.jfree.chart.labels.StandardPieSectionLabelGenerator
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import parser.PlayersCsvParser
import resolver.Resolver
import java.io.File

fun main(args: Array<String>) {
    val players = PlayersCsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    val countWithoutAgency = resolver.getCountWithoutAgency()
    val bestDefender = resolver.getBestScorerDefender()
    val expensivePlayer = resolver.getTheExpensiveGermanPlayerPosition()
    val rudestTeam = resolver.getTheRudestTeam()

    println("Количество игроков, интересы которых не представляет агенство: $countWithoutAgency")
    println("Автор наибольшего числа голов из числа защитников: ${bestDefender.first}, их количество: ${bestDefender.second}")
    println("Название позиции самого дорогого немецкого игрока: $expensivePlayer")
    println("Команда с наибольшим числом удалений на одного игрока: ${rudestTeam.name} из города ${rudestTeam.town}")

    val statisticData = resolver.getTopTenTransferCost()
    visualizeStatistics(statisticData)
}

fun visualizeStatistics(data: List<Pair<Team, Int>>) {
    val pieDataset = DefaultPieDataset<String>()

    data.forEach { (team, totalCost) ->
        pieDataset.setValue("${team.name} from ${team.town}", totalCost)
    }

    val pieChart = ChartFactory.createPieChart(
        "Топ-10 команд с наивысшей суммарной трансферной стоимостью", pieDataset
    )

    (pieChart.plot as PiePlot<*>).labelGenerator =
        StandardPieSectionLabelGenerator("Команда: {0}, Стоимость: {1}")

    ChartUtils.saveChartAsPNG(
        File("src/main/Highest-transfer-cost-teams.png"), pieChart, 1000, 1000
    )
}