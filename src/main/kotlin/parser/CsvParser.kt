package parser

import model.Player
import java.io.File

object CsvParser {
    fun parse(fileName: String): List<Player> {
        val players = mutableListOf<Player>()

        File(fileName).useLines { lines ->
            lines.drop(1).forEach { line ->
                val columns = line.split(";")
                val player = Player(
                    name = columns[0],
                    team = columns[1],
                    city = columns[2],
                    position = columns[3],
                    nationality = columns[4],
                    agency = if (columns[5].isNotEmpty()) columns[5] else null,
                    transferCost = columns[6].toLong(),
                    participations = columns[7].toInt(),
                    goals = columns[8].toInt(),
                    assists = columns[9].toInt(),
                    yellowCards = columns[10].toInt(),
                    redCards = columns[11].toInt()
                )
                players.add(player)
            }
        }

        return players
    }
}

