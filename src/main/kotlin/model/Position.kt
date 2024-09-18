package model

enum class Position {
    GOALKEEPER,
    DEFENDER,
    MIDFIELD,
    FORWARD,
    OTHER;

    companion object {
        fun getByValue(position: String): Position = entries.find { it.name == position } ?: OTHER
    }
}