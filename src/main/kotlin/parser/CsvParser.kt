package parser

import model.Player
import model.Team

import java.io.File

fun readPlayersFromFile(filePath: String): List<Player> {
    return File(filePath).readLines().drop(1).map { line ->
        val tokens = line.split(";")
        Player(
            name = tokens[0],
            team = Team(team = tokens[1], city = tokens[2]),
            position = tokens[3],
            nationality = tokens[4],
            agency = tokens[5],
            transferCost = tokens[6].toDouble(),
            participations = tokens[7].toInt(),
            goals = tokens[8].toInt(),
            assists = tokens[9].toInt(),
            yellowCards = tokens[10].toInt(),
            redCards = tokens[11].toInt()
        )
    }
}
