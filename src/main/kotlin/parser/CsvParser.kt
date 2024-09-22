package parser

import model.Player
import model.Position
import model.Team
import java.io.File
import java.util.Scanner

object CsvParser {
    private const val DELIMITER = ';'

    private var players: MutableList<Player> = mutableListOf()

    fun parse(path: String): List<Player> {
        val file = File(path)
        val scanner = Scanner(file)

        scanner.nextLine()

        while(scanner.hasNextLine()) {
            parsePlayerFromString(scanner.nextLine())
        }

        return players
    }

    private fun parsePlayerFromString(playerString: String) {
        val player = playerString.split(DELIMITER)

        players.add(
            Player(
                name = player[0],
                team = Team(name = player[1], city = player[2]),
                position = Position.valueOf(player[3]),
                nationality = player[4],
                agency = player[5],
                transferСost = player[6].toLong(),
                participations = player[7].toInt(),
                goals = player[8].toInt(),
                assists = player[9].toInt(),
                yellowCardCount = player[10].toInt(),
                redCardsCount = player[11].toInt()
            )
        )
    }
}