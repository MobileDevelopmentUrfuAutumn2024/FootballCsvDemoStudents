package model

enum class Position(val title: String) {
    MIDFIELD("Полузащитник"),
    DEFENDER("Защитник"),
    FORWARD("Нападающий"),
    GOALKEEPER("Вратарь"),
    NOT_FOUND_POSITION("Такой позиции нет"),
}