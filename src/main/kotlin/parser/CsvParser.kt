package parser

import model.Player
import java.io.File

object CsvParser {
    fun parse(filePath: String): List<Player> {
        val file = File(filePath)
        val players = mutableListOf<Player>()

        file.useLines { lines ->
            val linesList = lines.toList()
            linesList.first()
            linesList.drop(1).forEach { line ->
                val columns = line.split(';')
                players.add(Player(
                    name = columns[0],
                    team = columns[1],
                    city = columns[2],
                    position = columns[3],
                    nationality = columns[4],
                    agency = columns[5].ifBlank { null },
                    transferCost = columns[6].toInt(),
                    participation = columns[7].toInt(),
                    goals = columns[8].toInt(),
                    assists = columns[9].toInt(),
                    yellowCards = columns[10].toInt(),
                    redCards = columns[11].toInt()
                ))
            }
        }

        return players
    }
}
