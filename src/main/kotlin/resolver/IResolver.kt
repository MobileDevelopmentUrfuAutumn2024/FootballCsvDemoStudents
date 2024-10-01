package resolver
import model.Player
import model.Team

interface IResolver {

    // Выведите количество игроков, интересы которых не представляет агенство.
    //fun getCountWithoutAgency(): Int

    // Выведите автора наибольшего числа голов из числа защитников и их количество.
    //fun getBestScorerDefender(): Pair<String, Int>

    // Выведите русское название позиции самого дорогого немецкого игрока.
    //fun getTheExpensiveGermanPlayerPosition(): String

    // Выберите команду с наибольшим числом удалений на одного игрока.
    //fun getTheRudestTeam(): Team
    fun countPlayersWithoutAgency(players: List<Player>): Int
    fun topScorerAmongDefenders(players: List<Player>): Pair<String, Int>
    fun mostExpensiveGermanPlayerPosition(players: List<Player>): String
    fun teamWithMostRedCards(players: List<Player>): String
    fun visualizeGoalsVsTransferCost(players: List<Player>)
}