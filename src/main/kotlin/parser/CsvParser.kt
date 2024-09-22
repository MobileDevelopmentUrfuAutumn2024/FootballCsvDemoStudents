package parser

import model.Player
import model.Team
import java.io.File

fun File.toPlayers(): List<Player> {
    return readLines()
        .drop(1)
        .mapNotNull { it.toPlayer().getOrNull() }
}

private fun String.toPlayer(): Result<Player> {
    val props = split(';')
    return runCatching {
        Player(
            name = props[0],
            team = Team(
                name = props[1],
                city = props[2],
            ),
            position = enumValueOf(props[3]),
            nationality = props[4],
            agency = props[5],
            transferCost = props[6].toDouble(),
            participations = props[7].toInt(),
            goals = props[8].toInt(),
            assists = props[9].toInt(),
            yellowCards = props[10].toInt(),
            redCards = props[11].toInt(),
        )
    }
}