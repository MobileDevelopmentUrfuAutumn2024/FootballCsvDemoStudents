package resolver

import model.Player
import model.Team
import model.Position

class Resolver(private val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency() : Int {
        return players.count { it.agency.isNullOrBlank() };
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.position == Position.DEFENDER }
                      .maxBy  { it.goalsCount ?: 0 }
                      .let    { it.name to (it.goalsCount ?: 0) };
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { it.nation == "Germany" }
                      .maxBy  { it.transferCost }
                      .position?. name ?: "";
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }
                      .map     { (team, players) -> team to players.sumOf { it.redCardsCount } / players.count() }
                      .maxBy   { it.second } .first;
    }
}