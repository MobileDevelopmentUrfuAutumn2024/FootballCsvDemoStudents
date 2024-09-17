package resolver

import model.GoalKeeperFromTransferCost
import model.Player
import model.Team

interface IResolver {

    // Выведите количество игроков, интересы которых не представляет агенство.
    fun getCountWithoutAgency(listPlayers: List<Player>): Int

    // Выведите автора наибольшего числа голов из числа защитников и их количество.
    fun getBestScorerDefender(listPlayers: List<Player>): Pair<String, Int>

    // Выведите русское название позиции самого дорогого немецкого игрока.
    fun getTheExpensiveGermanPlayerPosition(listPlayers: List<Player>): String

    // Выберите команду с наибольшим числом удалений на одного игрока.
    fun getTheRudestTeam(listPlayers: List<Player>): Team

    /* Выберите список футболистов для демонстрации */
    /* зависимости количества забитых голов от трансферной стоимости для нападающих */
    fun getGoalKeeperFromTransferCost(listPlayers: List<Player>): List<GoalKeeperFromTransferCost>
}