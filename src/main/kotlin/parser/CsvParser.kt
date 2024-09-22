import model.EPosition
import model.Player
import model.Team
import java.io.File


object CsvParser {
    fun parse(filePath: String): List<Player> {
        val players = mutableListOf<Player>()

        File(filePath).useLines { lines ->
            val linesList = lines.toList()
            linesList.drop(1).forEach { line ->
                val columns = line.split(';')
                players.add(
                    Player(
                        name = columns[0],
                        team = Team(name = columns[1], city = columns[2]),
                        position = EPosition.entries.firstOrNull { it.name == columns[3] },
                        nationality = columns[4],
                        agency = columns[5].ifBlank { null },
                        transferCost = columns[6].toIntOrNull(),
                        participation = columns[7].toIntOrNull(),
                        goals = columns[8].toIntOrNull(),
                        assists = columns[9].toIntOrNull(),
                        yellowCards = columns[10].toIntOrNull(),
                        redCards = columns[11].toIntOrNull()
                    )
                )
            }
        }

        return players
    }
}