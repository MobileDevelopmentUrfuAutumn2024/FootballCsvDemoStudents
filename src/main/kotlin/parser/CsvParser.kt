package parser

import model.Player
import model.Team
import java.io.File
import java.util.Scanner

object CsvParser {

    private val players: MutableList<Player> = mutableListOf()

    fun readCSV(filePath: String): List<Player> {

        val file = File(filePath)
        val scanner = Scanner(file)

        scanner.nextLine()

        while (scanner.hasNextLine()) {
            playerFromString(scanner.nextLine())
        }

        return players

    }

    private fun playerFromString(player: String) {
        val playerString = player.split(";")

        players.add(
            Player(
                name = playerString[0],
                team = Team(name = playerString[1], city = playerString[2]),
                city = playerString[2],
                position = Player.Position.valueOf(playerString[3]),
                nationality = playerString[4],
                agent = playerString[5],
                price = playerString[6].toDouble(),
                countMatch = playerString[7].toInt(),
                countGoal = playerString[8].toInt(),
                countGoalBroadCast = playerString[9].toInt(),
                countYellowCard = playerString[10].toInt(),
                countRedCard = playerString[11].toInt()
            )
        )
    }
}
