package parser

import model.Player
import model.Position
import model.Team
import java.io.File
import java.util.Scanner

object CsvParser {

    fun parse(filename: String): List<Player> {
        val file = File(filename)
        val scanner = Scanner(file)

        val players: MutableList<Player> = mutableListOf()
        scanner.nextLine()

        while (scanner.hasNextLine()) {
            players.add(parsePlayerFromString(scanner.nextLine()))
        }

        return players.toList()
    }

    private fun parsePlayerFromString(string: String): Player {
        val list = string.split(";")

        return Player(
            name = list[0],
            team = Team(name = list[1], city = list[2]),
            position = Position.valueOf(list[3]),
            nationality = list[4],
            agency = list[5].ifBlank { null },
            transferCost = list[6].toIntOrNull() ?: 0,
            participationsCount = list[7].toIntOrNull() ?: 0,
            goalsCount = list[8].toIntOrNull() ?: 0,
            assistsCount = list[9].toIntOrNull() ?: 0,
            yellowCardsCount = list[10].toIntOrNull() ?: 0,
            redCardsCount = list[11].toIntOrNull() ?: 0
        )

    }

}
