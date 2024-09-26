package model

enum class Position(val title: String) {
    MIDFIELD("Центровой"),
    DEFENDER("Защитник"),
    FORWARD("Нападающий"),
    GOALKEEPER("Вратарь"),
    DEFAULT(""),
}