package parser

import model.Player
import model.Team
import model.Position
import java.io.File

object CsvParser {
    fun readPlayersFromFile(filePath: String): List<Player> {
        val players = mutableListOf<Player>()
        File(filePath).useLines { lines ->
            lines.drop(1).forEach { line ->
                val parts = line.split(";")
                if (parts.size == 12) {
                    val player = Player(
                        name = parts[0],
                        team = Team(parts[1], parts[2]),
                        position = Position.valueOf(parts[3].toUpperCase()),
                        nationality = parts[4],
                        agency = parts[5].takeIf { it.isNotBlank() },
                        transferCost = parts[6].toLong(),
                        participantsCount = parts[7].toInt(),
                        goals = parts[8].toInt(),
                        assists = parts[9].toInt(),
                        yellowCards = parts[10].toInt(),
                        redCards = parts[11].toInt()
                    )
                    players.add(player)
                }
            }
        }
        return players
    }
}
