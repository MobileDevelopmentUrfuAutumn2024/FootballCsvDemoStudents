package model

/**
 * Позиция
 * @param pos имя позиции на русском языке
 */
enum class Position(val pos: String) {
    MIDFIELD("Полузащитник"),
    DEFENDER("Защитник"),
    FORWARD("Нападающий"),
    GOALKEEPER("Вратарь"),
    OTHER("Другая роль")
}

/**
 * Подобрать позицию enum
 * @param pos позиция
 * @return enum позиции
 */
fun getPosition(pos: String): Position = Position.entries.find { it.name == pos }
    ?: Position.OTHER