package resolver

import model.Player
import model.Team

interface IResolver {

    // Выведите количество игроков, интересы которых не представляет агенство.
    fun getCountWithoutAgency(players: List<Player>): Int

    // Выведите автора наибольшего числа голов из числа защитников и их количество.
    fun getBestScorerDefender(players: List<Player>): Pair<String, Int>

    // Выведите русское название позиции самого дорогого немецкого игрока.
    fun getTheExpensiveGermanPlayerPosition(players: List<Player>): String

    // Выберите команду с наибольшим числом удалений на одного игрока.
    fun getTheRudestTeam(players: List<Player>): Team
}