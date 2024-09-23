package resolver

import model.Player
import model.Team
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.plot.CategoryPlot
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import java.awt.Color
import javax.swing.JFrame


class Resolver(private val players: List<Player>) : IResolver  {
  override fun getCountWithoutAgency(): Int {
    return players.count { it.agency.isNullOrBlank() }
  }

  override fun getBestScorerDefender(): Pair<String, Int> {
    val bestScorerDefender: Player = players
      .filter { it.position == Player.Position.DEFENDER }
      .maxByOrNull { it.goals } ?: throw NotFoundException("Best scorer defender not found")

    return bestScorerDefender
      .let { Pair(it.name, it.goals) }
  }

  override fun getTheExpensiveGermanPlayerPosition(): String {
    val expGermanPlayer: Player = players
      .filter { it.nationality == "Germany" }
      .maxByOrNull { it.transferCost }?: throw NotFoundException("Expensive german player not found")
    return expGermanPlayer.position.russianName
  }

  override fun getTheRudestTeam(): Team {
    val teamsAverageNumberRedCards = players
      .groupBy { it.team }.mapValues { (_, team) ->
        team.map { it.redCards }.average()
      }
    return teamsAverageNumberRedCards.maxByOrNull { it.value }?.key ?: throw NotFoundException("Team not found")
  }

  override fun getDependenceNumberGoalsScoredOnTransferCost() {
    val sortedPlayers = players.filter { it.position.name == "FORWARD" }.sortedBy { it.goals }

    val dataset = DefaultCategoryDataset()

    for (player in sortedPlayers) {
      dataset.addValue(player.transferCost, "TransferCoast", player.goals)
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

    val plot = chart.plot as CategoryPlot
    plot.renderer.setSeriesPaint(0, Color.BLUE)


    val panel = ChartPanel(chart)

    val frame = JFrame("Roman")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.contentPane.add(panel)
    frame.setSize(800, 600)
    frame.isVisible = true
  }

}

class NotFoundException(message: String?) : RuntimeException(message) {
}