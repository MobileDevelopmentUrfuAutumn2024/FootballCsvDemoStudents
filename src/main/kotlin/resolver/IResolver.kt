package resolver

import model.Player
import model.Team

interface IResolver {
    fun countPlayersWithoutAgency(players: List<Player>): Int
    fun topScorerAmongDefenders(players: List<Player>): Pair<String, Int>?
    fun mostExpensiveGermanPlayerPosition(players: List<Player>): String?
    fun teamWithMostRedCardsPerPlayer(players: List<Player>): Team?
}
