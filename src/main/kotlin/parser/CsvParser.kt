package parser

import java.io.File
import java.util.Scanner
import model.*

object CsvParser {
    fun getPlayers(): MutableList<Player> {
        var players: MutableList<Player> = mutableListOf()

        val sc = Scanner(File("src/main/resources/fakePlayers.csv"))

        sc.nextLine()
        while (sc.hasNextLine()) {
            val newLine = sc.nextLine().split(";")
            val player = Player(
                name = newLine[0],
                team = Team(newLine[1], newLine[2]),
                position = newLine[3],
                nationality = newLine[4],
                agency = newLine[5],
                transferCost = newLine[6].toInt(),
                participations = newLine[7].toInt(),
                goals = newLine[8].toInt(),
                assists = newLine[9].toInt(),
                yellowCards = newLine[10].toInt(),
                redCards = newLine[11].toInt()
            )
            players.add(player)
        }
        return players
    }
}