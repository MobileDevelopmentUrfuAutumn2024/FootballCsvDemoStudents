package resolver

import model.Player
import model.Position
import model.Team

class Resolver (
    private val players: List<Player>
) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count {
            it.agency.isNullOrEmpty()
        }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goalsCount }
            ?.let { it.name to it.goalsCount }
            ?: throw IllegalStateException("No best scorer defender")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position?.rusName
            ?: throw IllegalStateException("No German player found")
    }

    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team }
            .maxByOrNull { it.value.sumOf { player -> player.redCardsCount } / it.value.size }
            ?.key
            ?: throw IllegalStateException("No rudest team found")
    }

    override fun getShareOfPlayersByPosition(): Map<Position, Double> {
        val totalPlayers = players.size.toDouble()

        return players
            .groupBy { it.position }
            .mapValues { (_, playersInPosition) ->
                playersInPosition.size / totalPlayers
            }
    }

}