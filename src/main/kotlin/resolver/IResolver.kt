package resolver

import model.Player
import model.Team

interface IResolver {

    // Выведите количество игроков, интересы которых не представляет агенство.
    fun getCountWithoutAgency(players: List<Player>): Int {
        return players.count { it.agency.isNullOrEmpty() }
    }

    // Выведите автора наибольшего числа голов из числа защитников и их количество.
    fun getBestScorerDefender(players: List<Player>): Pair<String, Int> {
        return players
            .filter { it.position == "DEFENDER" }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals } ?: ("" to 0)
    }

    // Выведите русское название позиции самого дорогого немецкого игрока.
    fun getTheExpensiveGermanPlayerPosition(players: List<Player>): String {
        val positionMap = mapOf(
            "GOALKEEPER" to "Вратарь",
            "DEFENDER" to "Защитник",
            "MIDFIELD" to "Полузащитник",
            "FORWARD" to "Нападающий"
        )
        return players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferValue }
            ?.position
            ?.let { positionMap[it] ?: it } ?: "Неизвестная позиция"
    }

    // Выберите команду с наибольшим числом удалений на одного игрока.
    fun getTheRudestTeam(teams: List<Team>): Team {
        return teams.maxByOrNull { team ->
            val totalRedCards = team.players.sumOf { it.redCards }
            totalRedCards.toDouble() / team.players.size
        } ?: throw IllegalArgumentException("Нет данных для команд")
    }
}