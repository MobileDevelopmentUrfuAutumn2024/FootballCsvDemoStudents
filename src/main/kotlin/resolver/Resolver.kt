package resolver

import exception.NotFoundException
import graph.model.TeamTransferTotalCost
import model.Player
import model.Team

class Resolver(private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isNullOrBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.position == Player.Position.DEFENDER }
            .maxByOrNull { it.goalsCount }
            ?.let { it.name to it.goalsCount } ?: throw NotFoundException("Best scorer defender not found")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position?.russianName ?: throw NotFoundException("Expensive german player not found")
    }

    override fun getTheRudestTeam(): Team {
        val teamAverageRedCardsCount = players
            .groupBy { it.team }.entries
            .map { it -> Pair(it.key, it.value.sumOf { it.redCardsCount } / it.value.size) }

        val rudestTeamPair = teamAverageRedCardsCount
            .maxByOrNull { it.second } ?: throw NotFoundException("Rudest team not found")

        return rudestTeamPair.first
    }

    override fun getTeamsTotalTransferCosts(topLimit: Int): List<TeamTransferTotalCost> {
        if (topLimit <= 0) {
            throw IllegalArgumentException("Top limit must be positive number")
        }

        val teamsTotalTransferCost = players
            .groupBy { it.team }.entries
            .map { it -> TeamTransferTotalCost(it.key.name, it.value.sumOf { it.transferCost }) }
            .sortedByDescending { it.totalTransferCost }

        if (topLimit > teamsTotalTransferCost.size) {
            throw IllegalArgumentException("Top limit can not exceed teams count")
        }

        return teamsTotalTransferCost
            .take(topLimit)
    }

}