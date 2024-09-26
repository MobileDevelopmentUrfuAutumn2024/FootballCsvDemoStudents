package resolver

import model.Player
import model.Position
import model.Team
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import javax.swing.JFrame

class Resolver(private val players: List<Player>): IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count {it.agency.isNullOrBlank()}
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestDefender = players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals }
            ?: throw IllegalArgumentException("Not found player")
        return bestDefender
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensiveGermanPlayer = players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferСost } ?: throw IllegalArgumentException("Not found player")
        return expensiveGermanPlayer.position.translatedPositionName
    }

    override fun getTheRudestTeam(): Team {
        val groupedTeams = players.groupBy { it.team }.mapValues { (_, groupedPlayers) ->
            groupedPlayers.map { it.redCardsCount }.average()
        }

        return groupedTeams.maxByOrNull { it.value }?.key ?: throw IllegalArgumentException("Not found team")
    }

    override fun getDependenceOfGoalsOnCoastForForwards() {
        val sortedPlayers = players.filter { it.position.name == "FORWARD" }.sortedBy { it.goals }

        val dataset = DefaultCategoryDataset()

        for (player in sortedPlayers) {
            dataset.addValue(player.transferСost, "TransferCoast", player.goals)
        }

        val chart = ChartFactory.createLineChart(
            "Transfer Cost vs Goals For Forwards",
            "Goals",
            "Transfer Cost",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            true
        )

        val panel = ChartPanel(chart)

        val frame = JFrame("Krylov Gleb")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane.add(panel)
        frame.setSize(800, 600)
        frame.isVisible = true
    }

}