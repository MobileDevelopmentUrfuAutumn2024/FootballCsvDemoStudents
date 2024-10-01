package model

/**
 * Позиция игрока
 */
enum class Position(val rusName: String) {

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
    FORWARD("Нападающий")

}