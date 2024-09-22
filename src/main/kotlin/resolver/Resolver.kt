package resolver

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import javax.swing.JFrame
import org.jfree.chart.axis.NumberAxis
import java.text.NumberFormat

class Resolver : IResolver {

    override fun countPlayersWithoutAgency(players: List<Player>): Int {
        return players.count { it.agency.isNullOrEmpty() }
    }

    override fun topScorerAmongDefenders(players: List<Player>): Pair<String, Int> {
        return players.filter { it.position == "DEFENDER" }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals } ?: "" to 0
    }

    override fun mostExpensiveGermanPlayerPosition(players: List<Player>): String {
        return players.filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position ?: "Unknown"
    }

    override fun teamWithMostRedCards(players: List<Player>): String {
        return players.groupBy { it.team }
            .mapValues { (_, teamPlayers) ->
                teamPlayers.sumBy { it.redCards } / teamPlayers.size.toDouble()
            }
            .maxByOrNull { it.value }
            ?.key ?: "No team"
    }

    override fun visualizeGoalsVsTransferCost(players: List<Player>) {
        val forwards = players.filter { it.position == "FORWARD" }
        val series = XYSeries("Goals vs Transfer Cost")

        forwards.forEach { player ->
            series.add(player.transferCost.toDouble(), player.goals.toDouble())
        }

        val dataset = XYSeriesCollection()
        dataset.addSeries(series)

        val chart: JFreeChart = ChartFactory.createScatterPlot(
            "Забитые голы vs Трансферная стоимость",
            "Трансферная стоимость",
            "Количество Голов",
            dataset
        )

        // Настройка оси X (стоимость трансфера)
        val plot = chart.xyPlot
        val xAxis = plot.domainAxis as NumberAxis
        xAxis.label = "Трансферная стоимость"
        xAxis.numberFormatOverride = NumberFormat.getNumberInstance()

        // Отображение графика
        val frame = JFrame("Забитые голы vs Трансферная стоимость")
        val chartPanel = ChartPanel(chart)
        frame.contentPane = chartPanel
        frame.pack()
        frame.isVisible = true
    }
}