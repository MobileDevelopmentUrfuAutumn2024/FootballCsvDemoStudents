package resolver

import model.Player
import model.Team

interface IResolver {
    fun countPlayersWithoutAgency(agencyName: String, players: List<Player>): Int
    fun topScoringDefender(players: List<Player>): Pair<String, Int>?
    fun positionOfMostExpensiveGermanPlayer(players: List<Player>): String?
    fun teamWithHighestAverageRedCards(teams: List<Team>, players: List<Player>): Team?
}
