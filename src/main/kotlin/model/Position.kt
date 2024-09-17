package model

enum class Position {

    MIDFIELD,
    DEFENDER,
    FORWARD,
    GOALKEEPER;

    companion object {
        fun getByValue(value: String) = entries.find { it.name == value } ?: throw IllegalArgumentException("No enum constant with value $value")
    }
}