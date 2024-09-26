package resolver

import model.Player
import model.PlayerPosition
import model.Team

class Resolver(private val teams: List<Team>) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return getAllPlayers(teams).count { it.transferData.agency.isEmpty() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return getAllPlayers(teams)
            .filter { it.personData.position == PlayerPosition.DEFENDER }
            .map { it.personData.name to it.statisticsData.goals }
            .maxByOrNull { it.second } ?: ("None" to 0)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val engName = getAllPlayers(teams)
            .filter { it.personData.nationality == "Germany" }
            .maxByOrNull { it.transferData.cost }?.personData?.position.toString()
        return when (engName) {
            PlayerPosition.FORWARD.strName -> "Нападающий"
            PlayerPosition.GOALKEEPER.strName -> "Вратарь"
            PlayerPosition.DEFENDER.strName -> "Защитник"
            PlayerPosition.MIDFIELD.strName -> "Центровой"
            else -> { throw Exception("Неизвестная позиция")}
        }
    }

    override fun getTheRudestTeam(): Team {
        return teams.map {
            it to it.players.sumOf { player -> player.statisticsData.redCards } / it.players.count()
        }.maxByOrNull { it.second }?.first ?: throw Exception("Error in ${::getTheRudestTeam.name}")
    }

    private fun getAllPlayers(teams : List<Team>) : List<Player> = teams.flatMap { it.players }
}