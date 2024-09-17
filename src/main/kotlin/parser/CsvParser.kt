package parser

import model.Player
import model.Position
import model.Team
import java.io.InputStream
import java.util.Scanner

object CsvParser {

    private val players: MutableList<Player> = mutableListOf()

    fun parse(resourcePath: String): List<Player> {
        val inputStream: InputStream = CsvParser::class.java.classLoader.getResourceAsStream(resourcePath)
            ?: throw IllegalArgumentException("File not found!")

        val scanner = Scanner(inputStream)
        scanner.nextLine()

        while (scanner.hasNextLine()) {
            parsePlayerFromString(scanner.nextLine())
        }

        return players
    }

    private fun parsePlayerFromString(playerString: String) {
        val playerParts: List<String> = playerString.split(";")

        players.add(
            Player(
                name = playerParts[0],
                team = Team(name = playerParts[1], city = playerParts[2]),
                position = Position.getByValue(playerParts[3]),
                agency = playerParts[5],
                nationality = playerParts[4],
                transferCost = playerParts[6].toIntOrNull().orZero(),
                participationCount = playerParts[7].toIntOrNull().orZero(),
                goalsCount = playerParts[8].toIntOrNull().orZero(),
                assistsCount = playerParts[9].toIntOrNull().orZero(),
                yellowCardsCount = playerParts[10].toIntOrNull().orZero(),
                redCardsCount = playerParts[11].toIntOrNull().orZero()
            )
        )
    }

    private fun Int?.orZero() = this ?: 0
}