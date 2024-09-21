package model

/**
 * Игрок
 */
class Player (
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
     * Национальность
     */
    val nationality: String,
    /**
     * Агентство
     */
    val agency: String?,
    /**
     * Трансферная стоимость
     */
    val transferCost: Long,
    /**
     * Статистика
     */
    val statistics: Statistics
) {
    /**
     * Статистика игрока
     */
    data class Statistics(
        /**
         * Количество участий в матчах
         */
        var participations: Int,
        /**
         * Количество голов
         */
        var goals: Int,
        /**
         * Количество голевых передач
         */
        var assists: Int,
        /**
         * Количество желтых карточек
         */
        var yellowCards: Int,
        /**
         * Количество красных карточек
         */
        var redCards: Int
    )

    /**
     * Позиция игрока
     */
    enum class Position(var localizeName: String) {
        /**
         * Вратарь
         */
        GOALKEEPER("Вратарь"),
        /**
         * Защитник
         */
        DEFENDER("Защитник"),
        /**
         * Полузащитник
         */
        MIDFIELD("Полузащитник"),
        /**
         * Нападающий
         */
        FORWARD("Нападающий");
    }
}
