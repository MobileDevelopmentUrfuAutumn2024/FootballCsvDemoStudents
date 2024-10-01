package resolver

import model.Position
import model.Team

interface IResolver {

    /**
     * Количество игроков, интересы которых не представляет агенство
     */
    fun getCountWithoutAgency(): Int

    /**
     * Автор наибольшего числа голов из числа защитников и их количество
     */
    fun getBestScorerDefender(): Pair<String, Int>

    /**
     * Русское название позиции самого дорогого немецкого игрока
     */
    fun getTheExpensiveGermanPlayerPosition(): String

    /**
     * Команда с наибольшим числом удалений на одного игрока
     */
    fun getTheRudestTeam(): Team

    /**
     * Возвращает карту вида [позиция: доля игроков от общего числа в этой позиции]
     */
    fun getShareOfPlayersByPosition(): Map<Position, Double>

}