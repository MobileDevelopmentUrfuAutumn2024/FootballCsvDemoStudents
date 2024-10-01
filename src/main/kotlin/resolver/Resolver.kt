package resolver

import model.Player
import model.Team

class Resolver : IResolver {

    private val positionTranslations = mapOf(
        "GOALKEEPER" to "Вратарь",
        "DEFENDER" to "Защитник",
        "MIDFIELD" to "Полузащитник",
        "FORWARD" to "Нападающий",
    )

    override fun countPlayersWithoutAgency(agencyName: String, players: List<Player>): Int {
        return players.count { it.agency.isEmpty() }
    }

    override fun topScoringDefender(players: List<Player>): Pair<String, Int>? {
        val defenders = players.filter { it.position.equals("DEFENDER", ignoreCase = true) }
        return defenders.maxByOrNull { it.goals }?.let { it.name to it.goals }
    }

    override fun positionOfMostExpensiveGermanPlayer(players: List<Player>): String? {
        val germanPlayers = players.filter { it.nationality.equals("Germany", ignoreCase = true) }
        val mostExpensivePlayer = germanPlayers.maxByOrNull { it.transferCost }
        return mostExpensivePlayer?.position?.let { positionTranslations[it] }
    }

    override fun teamWithHighestAverageRedCards(teams: List<Team>, players: List<Player>): Team? {
        val teamRedCards = players.groupBy { it.team }.mapValues { entry ->
            entry.value.sumOf { it.redCards } / entry.value.size.toDouble()
        }
        return teamRedCards.maxByOrNull { it.value }?.key
    }
}
