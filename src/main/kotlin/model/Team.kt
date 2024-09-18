package model

/**
 * Команда
 * @param name имя команды
 * @param players список игроков
 */
data class Team(
    val name: String,
    val players: List<Player>
)