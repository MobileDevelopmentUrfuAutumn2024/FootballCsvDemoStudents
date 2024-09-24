package parser

import model.Player
import model.Position
import java.io.File
import java.util.Scanner

object CsvParser {
    fun parsePlayers(path: String): List<Player> {
        val file = File(path)
        val scanner = Scanner(file)
        scanner.nextLine()

        val players: MutableList<Player> = mutableListOf()

        while (scanner.hasNextLine()) {
            players.add(parsePlayerLine(scanner.nextLine()))
        }

        return players
    }

    private fun parsePlayerLine(str: String, delimiter: Char = ';'): Player {
        val playerList = str.split(delimiter)

        val name = playerList[0]
        val teamName = playerList[1]
        val city = playerList[2]
        val position = playerList[3]
        val nationality = playerList[4]
        val agency = playerList[5].ifBlank { null }
        val transferCost = playerList[6].toLong()
        val participations = playerList[7].toInt()
        val goals = playerList[8].toInt()
        val assists = playerList[9].toInt()
        val yellowCards = playerList[10].toInt()
        val redCards = playerList[11].toInt()

        return Player(
            name,
            teamName,
            city,
            Position.valueOf(position),
            nationality,
            agency,
            transferCost,
            participations,
            goals,
            assists,
            yellowCards,
            redCards
        )
    }
}