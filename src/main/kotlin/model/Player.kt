package model

/**
 * Представление игрока
 */
data class Player (

    /**
     * Имя
     */
    val name: String,
    /**
     * Команда
     */
    val team: Team,
    /**
     * Позиция
     */
    val position: Position,
    /**
     * Агентство
     */
    val agency: String?,
    /**
     * Национальность
     */
    val nationality: String,
    /**
     * Трансферная стоимость
     */
    val transferCost: Int,
    /**
     * Кол-во участий в матчах
     */
    val participationCount: Int,
    /**
     * Кол-во голов
     */
    val goalsCount: Int,
    /**
     * Кол-во голевых передач
     */
    val assistsCount: Int,
    /**
     * Кол-во желтых карточек
     */
    val yellowCardsCount: Int,
    /**
     * Кол-во красных карточек
     */
    val redCardsCount: Int

)
