package parser

import model.Player
import model.Team
import java.io.File
import java.util.*

/**
 * Парсер CSV файла с данными об игроках
 */
object CsvParser {

    /**
     * Парсинг игроков из файла
     *
     * @return список игроков
     */
    fun parse(path: String): List<Player> {
        val players: MutableList<Player> = mutableListOf()
        val file = File(path)
        val scanner = Scanner(file)
        scanner.nextLine()
        while (scanner.hasNextLine()) {
            players.add(parsePlayer(scanner.nextLine()))
        }
        return players
    }

    private fun parsePlayer(playerLine: String): Player {
        val fields: List<String> = playerLine.split(";")
        return Player(
            name = fields[0],
            team = Team(
                name = fields[1],
                city = fields[2]
            ),
            position = Player.Position.valueOf(fields[3]),
            nationality = fields[4],
            agency = fields[5],
            transferCost = fields[6].toLongOrNull()?:0,
            statistics = Player.Statistics(
                participations = fields[7].toIntOrNull()?:0,
                goals = fields[8].toIntOrNull()?:0,
                assists = fields[9].toIntOrNull()?:0,
                yellowCards = fields[10].toIntOrNull()?:0,
                redCards = fields[11].toIntOrNull()?:0
            )
        )
    }
}