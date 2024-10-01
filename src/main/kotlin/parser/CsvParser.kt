package parser

import model.FootballPos
import model.Player
import model.PlayerStatistics
import model.Team
import java.io.File
import java.util.Scanner

object CsvParser {

    private var players: MutableList<Player> = mutableListOf()

    fun parse(path: String): List<Player>{
        val file = File(path)
        val scanner = Scanner(file)
        scanner.nextLine()

        while (scanner.hasNextLine()){
            parsePlayerFromString(scanner.nextLine())
        }
        return players
    }

    private fun parsePlayerFromString(playerString: String){
        val playerString = playerString.split(";")

        players.add(
            Player(
                name = playerString[0],
                team = Team(
                    name = playerString[1],
                    city = playerString[2]
                ),
                position = FootballPos.valueOf(playerString[3]),
                nation = playerString[4],
                agency = playerString[5],
                transferCost = playerString[6].toInt(),
                playerStat = PlayerStatistics(
                    matchesCount = playerString[7].toInt(),
                    goals = playerString[8].toInt(),
                    assists = playerString[9].toInt(),
                    yellowCardsCount = playerString[10].toInt(),
                    redCardsCount = playerString[11].toInt()
                )
            )
        )
    }

}
