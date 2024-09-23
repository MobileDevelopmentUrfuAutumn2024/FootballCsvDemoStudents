package resolver

import model.Player
import model.Position
import model.Team

class Resolver(private val players: List<Player>): IResolver{

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isEmpty() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.position == Position.DEFENDER }
            .maxBy { it.goals ?: 0 }
            .let { it.name to (it.goals ?: 0) }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { it.nationality == "Germany" }
            .maxBy { it.transferCost ?: 0 }.position?.name ?: ""
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }
            .map { (team, players) -> team to players.sumOf { it.redCards ?: 0 } / players.count() }
            .maxBy { it.second }.first
    }

}