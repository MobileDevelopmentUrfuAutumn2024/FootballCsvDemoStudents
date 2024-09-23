package resolver

import model.Player
import model.Position
import model.Team

class Resolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count {it.agency == ""}
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestScoreDefender = players
            .filter { it.position == Position.DEFENDER }
            .maxBy { it.goalsCount }

        return Pair(bestScoreDefender.name, bestScoreDefender.goalsCount)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensiveGermanPlayer = players
            .filter { it.nationality == "Germany" }
            .maxBy { it.participationCount }

        return expensiveGermanPlayer.position.title
    }

    override fun getTheRudestTeam(): Team {
        val rudestTeam = players
            .groupBy { it.team }
            .maxBy { it.value.map {player -> player.redCardsCount }.average() }

        return rudestTeam.key
    }

    override fun getTopTenTransferCostTeams(): List<Pair<Team,Int>> {
        val topTenCostTeams = players
            .groupBy { it.team }
            .mapValues { it.value.sumOf { player -> player.transferCost } }
            .toList()
            .sortedBy { it.second }

        return topTenCostTeams.take(10)
    }
}