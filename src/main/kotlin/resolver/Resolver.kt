package resolver

import model.Player
import model.Position
import model.Team

class Resolver(private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goalsCount }
            ?.let { it.name to it.goalsCount }
            ?: throw IllegalArgumentException("The best scorer defender is not found")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position?.ruName
            ?: throw IllegalArgumentException("The expensive germany player position is not found")
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }
            .mapValues { (_, players) ->
                players.map { it.redCardsCount }.average()
            }
            .maxByOrNull { it.value }?.key
            ?: throw IllegalArgumentException("The rudest team is not found")
    }

    override fun getPositionDivision(): Map<String, Int> {
        return players.groupBy { it.position.ruName }
            .mapValues { it.value.size }
    }
}