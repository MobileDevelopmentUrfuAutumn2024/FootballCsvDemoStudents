package parser

import model.PersonData
import model.Player
import model.StatisticsData
import model.Team
import model.TransferData
import java.io.File
import java.util.*

object CsvParser {
    private val teams: MutableList<Team> = mutableListOf()

    fun parse(path: String): List<Team> {
        val file = File(path)
        val scanner = Scanner(file)
        scanner.nextLine()

        while (scanner.hasNextLine()) {
            parsePlayerFromString(scanner.nextLine())
        }

        return teams
    }

    private fun parsePlayerFromString(playerString: String): Player {
        val playerData = playerString.split(";")
        val player = Player(
            personData = PersonData(playerData[0], playerData[4], playerData[3]),
            transferData = TransferData(playerData[5], playerData[6]),
            statisticsData = StatisticsData(
                playerData[7].toInt(),
                playerData[8].toInt(),
                playerData[9].toInt(),
                playerData[10].toInt(),
                playerData[11].toInt()
            )
        )

        val teamName = playerData[1]
        val teamCity = playerData[2]
        val teamIndex = teams.indexOfFirst { it.getName() == teamName }
        if (teamIndex != -1) {
            val team = teams[teamIndex]
            val players = team.getPlayers().toMutableList()
            players.add(player)
            teams[teamIndex] = Team(teamName, teamCity, players)
        } else {
            teams.add(Team(teamName, teamCity, listOf(player)))
        }

        return player
    }

}