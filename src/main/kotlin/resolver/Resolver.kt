package resolver

import model.Player
import model.Team

class Resolver(
    private val players: List<Player>
) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isNullOrEmpty() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.position == Player.Position.DEFENDER }
            .maxByOrNull { it.statistics.goals }
            ?.let { it.name to it.statistics.goals }
            ?:throw NoSuchElementException("Not found defender")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position?.localizeName
            ?:throw NoSuchElementException("Not found German player position")
    }

    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team }
            .maxByOrNull { it.value.sumOf { p -> p.statistics.redCards } / it.value.size.toDouble() }
            ?.key
            ?:throw NoSuchElementException("Not found rudest team")

    }

    override fun getSharePlayersDifferentCountries(): Map<String, Double> {
        return players
            .groupingBy { it.nationality }
            .eachCount()
            .mapValues { it.value.toDouble() / players.size }
    }

}