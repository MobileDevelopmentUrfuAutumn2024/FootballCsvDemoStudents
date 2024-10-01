package resolver

import model.Player
import model.Team

class Resolver(private val players: List<Player>) : IResolver {

    private val translationOfPositionsIntoRussian = mapOf(
        "GOALKEEPER" to "Вратарь",
        "DEFENDER" to "Защитник",
        "FORWARD" to "Нападающий",
        "MIDFIELD" to "Полузащитник",
    )

    override fun getCountWithoutAgency(): Int {
        var countWithoutAgency = 0

        for (player in players) {
            if (player.agency == null) {
                countWithoutAgency += 1
            }
        }

        return countWithoutAgency
    }

    override fun getBestScorerDefender(): List<Pair<String, Int>> {
        val defenders = players.filter { it.position.equals("DEFENDER", ignoreCase = true) }
        if (defenders.isEmpty()) {
            println("Нет защитников в списке игроков.")
            return emptyList()
        }
        val maxGoals = defenders.maxOfOrNull { it.goals } ?: run {
            println("Не удалось определить максимальное количество голов среди защитников.")
            return emptyList()
        }
        return defenders.filter { it.goals == maxGoals }
            .map { it.name to it.goals }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val translatedPosition = players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position
            ?.let { translationOfPositionsIntoRussian[it] }

        return translatedPosition ?: when {
            players.none { it.nationality == "Germany" } -> "Нет немецких игроков в команде"
            else -> "Неизвестная позиция"
        }
    }

    override fun getTheRudestTeam(): Team =
        players
            .groupBy { it.team }
            .mapValues { (_, teamPlayers) ->
                teamPlayers.map { it.redCards }.average()
            }
            .maxByOrNull { it.value }
            ?.let { Team(it.key) }
            ?: Team("Нет команды")
}