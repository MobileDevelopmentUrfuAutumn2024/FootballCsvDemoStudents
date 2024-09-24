package resolver

import chart.ChartManager
import model.Player
import model.Position
import model.Team
import java.io.File
import java.nio.file.Paths


class Resolver(private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == null }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestDefenderByGoals = players
            .filter { it.position == Position.DEFENDER }
            .maxBy { it.goals }

        return Pair(bestDefenderByGoals.name, bestDefenderByGoals.goals)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality == "Germany" }
            .maxBy { it.transferCost }
            .position.localization
    }

    override fun getTheRudestTeam(): Team {
        val teams = players
            .groupBy { it.teamName }
            .map { (teamName, playersList) -> Team(teamName, playersList) }


        return teams.maxBy { it.getAverageRedCards() }
    }

//    Вариант 2.
//    Выведите топ-10 команд с наивысшей суммарной трансферной стоимостью с демонстрацией этих стоимостей.
    fun topExpensiveTeams() {
        val topExpensiveTeams = players
            .groupBy { it.teamName }
            .map { (teamName, playersList) -> Team(teamName, playersList) }
            .sortedByDescending { it.getTotalTransferCost() }
            .take(10)

        val dataSet = ChartManager.createDataSet(topExpensiveTeams)
        val chart = ChartManager.createBarChart(dataSet)

        val file =
            File("${Paths.get("").toAbsolutePath()}\\src\\main\\resources\\topTeamsChart.png")

        ChartManager.saveChart(file, chart)
        ChartManager.displayChart(chart)
    }
}