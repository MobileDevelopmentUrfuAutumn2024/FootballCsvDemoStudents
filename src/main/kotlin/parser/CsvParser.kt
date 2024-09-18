package parser

import model.Player
import model.Team
import java.io.File
import java.util.Scanner

object CsvParser {

    private var players: MutableList<Player> = mutableListOf()

    fun parse(path: String): List<Player> {
        val file = File(path)
        val scanner = Scanner(file)

        scanner.nextLine()

        while (scanner.hasNextLine()) {
            parsePlayerFromString(scanner.nextLine())
        }

        return players
    }

    private fun parsePlayerFromString(playerString: String) {
        val playerStringSplitted = playerString.split(";")

        players.add(
            Player(
                name = playerStringSplitted[0],
                team = Team(name = playerStringSplitted[1], city = playerStringSplitted[2]),
                position = Player.Position.valueOf(playerStringSplitted[3]),
                nationality = playerStringSplitted[4],
                agency = playerStringSplitted[5],
                transferCost = playerStringSplitted[6].toLongOrDefault(),
                participationCount = playerStringSplitted[7].toIntOrDefault(),
                goalsCount = playerStringSplitted[8].toIntOrDefault(),
                assistsCount = playerStringSplitted[9].toIntOrDefault(),
                yellowCardsCount = playerStringSplitted[10].toIntOrDefault(),
                redCardsCount = playerStringSplitted[11].toIntOrDefault()
            )
        )
    }

    private fun String?.toIntOrDefault(default: Int = 0): Int {
        return this?.toIntOrNull()?:default
    }

    private fun String?.toLongOrDefault(default: Long = 0): Long {
        return this?.toLongOrNull()?:default
    }
}