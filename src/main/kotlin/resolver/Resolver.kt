package resolver

import model.Player
import model.Position
import model.Team

class Resolver(val players: List<Player>) : IResolver{
    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isNullOrBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestScorerDefender: Player = players.filter { it.position==Position.DEFENDER }
            .maxByOrNull { it.goals } ?: throw Exception("Best scorer Defender has not defined")
        return bestScorerDefender.let { Pair(it.name, it.goals) }

    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensivePlayer = players.filter { it.nationality=="Germany" }
            .maxByOrNull { it.transferCost } ?: throw Exception("The most expensive German player has not found")
        return  expensivePlayer.let {
            when (it.position) {
                Position.DEFENDER -> "Защитник"
                Position.MIDFIELD -> "Полузащитник"
                Position.FORWARD -> "Нападающий"
                Position.GOALKEEPER -> "Вратарь" }
        }
    }

    override fun getTheRudestTeam(): Team {
        val rudestTeam = players.groupBy { it.team }
            .map { it -> Pair(it.key, it.value.sumOf { it.redCardsCount } / it.value.size) }
            .maxByOrNull { it.second } ?: throw Exception("The rudest team has not found")
        return rudestTeam.first
    }

    override fun getPercentagePositionOfPlayers(): Map<String, Double> {
        val countAllPlayers: Int = players.map { it.position }.count()
        return players.groupBy { it.position.toString() }
            .mapValues{ (_, value) -> value.count() / countAllPlayers.toDouble()}
    }

}