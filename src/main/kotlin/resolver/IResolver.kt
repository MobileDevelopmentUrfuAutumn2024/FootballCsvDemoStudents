package resolver

import graph.model.TeamTransferTotalCost
import model.Team

interface IResolver {

    // Выведите количество игроков, интересы которых не представляет агенство.
    fun getCountWithoutAgency(): Int

    // Выведите автора наибольшего числа голов из числа защитников и их количество.
    fun getBestScorerDefender(): Pair<String, Int>

    // Выведите русское название позиции самого дорогого немецкого игрока.
    fun getTheExpensiveGermanPlayerPosition(): String

    // Выберите команду с наибольшим числом удалений на одного игрока.
    fun getTheRudestTeam(): Team

    // Выведите топ-n команд с наивысшей суммарной трансферной стоимостью.
    fun getTeamsTotalTransferCosts(topLimit: Int): List<TeamTransferTotalCost>

}