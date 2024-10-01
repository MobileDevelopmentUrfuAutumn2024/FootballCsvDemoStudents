package resolver

import model.Player
import model.Team

class Resolver : IResolver {
    override fun countPlayersWithoutAgency(players: List<Player>): Int {
        return players.count { it.agency == null }
    }

    override fun topScorerAmongDefenders(players: List<Player>): Pair<String, Int>? {
        return players.filter { it.position == model.Position.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals }
    }

    override fun mostExpensiveGermanPlayerPosition(players: List<Player>): String? {
        return players.filter { it.nationality.equals("Germany", ignoreCase = true) }
            .maxByOrNull { it.transferCost }
            ?.position?.russianName
    }

    override fun teamWithMostRedCardsPerPlayer(players: List<Player>): Team? {
        return players.groupBy { it.team }
            .mapValues { (_, teamPlayers) ->
                teamPlayers.sumOf { it.redCards } / teamPlayers.size.toDouble()
            }
            .maxByOrNull { it.value }
            ?.key
    }

    fun playerShareByNationality(players: List<Player>): Map<String, Double> {
        val totalPlayers = players.size

        val nationalityCount = players.groupingBy { it.nationality }.eachCount()

        return nationalityCount.mapValues { (nationality, count) ->
            (count.toDouble() / totalPlayers) * 100
        }
    }
}
