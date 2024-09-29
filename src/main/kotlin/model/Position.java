
package model;

enum class Position {
    GOALKEEPER,
    DEFENDER,
    MIDFIELD,
    FORWARD,
    NULL;

    companion object {
        fun getByValue(position: String): Position = entries.find { it.name == position } ?: NULL;
    }
}