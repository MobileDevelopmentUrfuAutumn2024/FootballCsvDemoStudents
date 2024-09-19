package model

enum class Position(val PositionName: String) {
    MIDFIELD("Центровой"),
    DEFENDER("Защитник"),
    FORWARD("Главный"),
    GOALKEEPER("Голозабивальщик"),
    Other("Не пойми кто");
}