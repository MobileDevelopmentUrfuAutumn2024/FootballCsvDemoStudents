package resolver

import model.Player
import model.Position
import model.Team

class Resolver(val players: List<Player>) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isNullOrBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestScorerDefender: Player = players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goalsCount } ?: throw Exception("Best scorer defender not found")
        return bestScorerDefender
            .let { Pair(it.name, it.goalsCount) }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensivePlayer = players.filter { it.nationality=="Germany" }.maxByOrNull { it.transferCost }
            ?: throw Exception("Expensive german player not found")
        return  expensivePlayer.let {
            when (it.position) {
                Position.DEFENDER -> "Защитник"
                Position.GOALKEEPER -> "Вратарь"
                Position.MIDFIELD -> "Полузащитник"
                Position.FORWARD -> "Нападающий"
                else -> "Другая позиция" }
        }
    }

    override fun getTheRudestTeam(): Team {
        val rudestTeamPair = players
            .groupBy { it.team }.entries
            .map { it -> Pair(it.key, it.value.sumOf { it.redCardsCount } / it.value.size) }
            .maxByOrNull { it.second } ?: throw Exception("Rudest team not found")
        return rudestTeamPair.first
    }

    override fun getCountPlayersByPosition(): Map<String, Double> {
        val totalCount: Int = players.map { it.position }.count()
        return players.groupBy { it.position.toString() }
            .mapValues{ (_, value) -> value.count() / totalCount.toDouble()}
    }
}