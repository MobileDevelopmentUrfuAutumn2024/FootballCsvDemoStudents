package resolver

import model.Team

/**
 * Задачи
 */
interface IResolver {

    /**
     * Получить количество игроков, интересы которых не представляет агенство
     * @return количество игроков
     */
    fun getCountWithoutAgency(): Int

    /**
     * Выведите автора наибольшего числа голов из числа защитников и их количество.
     * @return наилучший игрок(имя и количество очков)
     */
    fun getBestScorerDefender(): Pair<String, Int>

    /**
     * Получить русское название позиции самого дорогого немецкого игрока.
     * @return название позиции
     */

    fun getTheExpensiveGermanPlayerPosition(): String

    /**
     * Получить команду с наибольшим числом удалений на одного игрока.
     * @return команда
     */
    fun getTheRudestTeam(): Team
}