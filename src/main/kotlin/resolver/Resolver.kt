package resolver

import model.FootballPosition
import model.Player
import model.Team

class Resolver(
    private val players: List<Player>
) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count { it.Agency.isNullOrEmpty() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.Position == FootballPosition.DEFENDER }
            .maxByOrNull { it.Stats.Goals }?.let {
                it.Name to it.Stats.Goals
            } ?: throw NoSuchElementException("No defenders found")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { it.Nationality == "Germany" }
            .maxByOrNull { it.TransferCost }?.Position?.toRussian()
            ?: throw NoSuchElementException("No german player found")
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.Team }
            .maxByOrNull { (_, players) -> players.size / players.sumOf { it.Stats.RedCards } }?.key
            ?: throw NoSuchElementException("No rudest team found")
    }

}