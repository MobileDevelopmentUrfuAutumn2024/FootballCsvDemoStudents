package model

enum class Position {

    MIDFIELD,
    DEFENDER,
    FORWARD,
    GOALKEEPER;

    companion object {
        fun getByValue(value: String) = entries.find { it.name == value } ?: throw IllegalArgumentException("No enum constant with value $value")

        fun translate(maxCountPlayer: String): String {

            return when (maxCountPlayer) {
                MIDFIELD.name -> "Полузащитник"
                DEFENDER.name -> "Защитник"
                FORWARD.name -> "Нападающий"
                GOALKEEPER.name -> "Вратарь"
                else -> "Not Found"
            }
        }
    }
}