package resolver

import model.Player
import model.Position
import model.Team

class Resolver (private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isNullOrBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goalsCount }
            ?.let { it.name to it.goalsCount }
            ?: throw Exception("Not found player")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position?.russianName ?: throw Exception("Not found player")
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }
            .maxByOrNull { it.value.sumOf { player -> player.redCardsCount } / it.value.size }
            ?.key
            ?: throw Exception("Not found team ")
    }

    override fun getProportionsOfPlayers(): Map<String, Int> {
        return players.groupBy { it.position.russianName }
            .mapValues { it.value.size }
    }
}
