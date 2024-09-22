package resolver

import model.Player
import model.Team

class Resolver(private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == null }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.position == Player.Position.DEFENDER }
            .maxBy { it.goalsCount }
            .let { it.name to it.goalsCount }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality == "Germany"  }
            .maxBy { it.transferCost }
            .position.russianName
    }

    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team }
            .maxBy { (_, value) -> value.sumOf { it.redCardsCount } / value.size }
            .key
    }
}