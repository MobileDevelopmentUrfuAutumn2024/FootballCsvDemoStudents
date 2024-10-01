package resolver

import model.Player
import model.Team

class Resolver(private val players: List<Player>) : IResolver {

    private val positionTranslations = mapOf(
        "GOALKEEPER" to "Вратарь",
        "DEFENDER" to "Защитник",
        "MIDFIELD" to "Полузащитник",
        "FORWARD" to "Нападающий"
    )

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == null }
    }

    override fun getBestScorerDefender(): List<Pair<String, Int>> {
        val maxGoals = players
            .filter { it.position == "DEFENDER" }
            .maxOfOrNull { it.goals }
            ?: return emptyList()

        return players
            .filter { it.position == "DEFENDER" && it.goals == maxGoals }
            .map { it.name to it.goals }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position
            ?.let { positionTranslations[it] ?: "Неизвестная позиция" }
            ?: "Неизвестная позиция"
    }

    override fun getTheRudestTeam(): Team {
        val teamRedCards = players
            .groupBy { it.team }
            .mapValues { entry ->
                entry.value.sumOf { it.redCards.toDouble() / (entry.value.size) }
            }
        return teamRedCards.maxByOrNull { it.value }
            ?.let { Team(it.key) } ?: Team("No team")
    }
}
