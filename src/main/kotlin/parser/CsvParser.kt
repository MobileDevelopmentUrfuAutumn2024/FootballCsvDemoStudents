package parser

import model.Player
import model.Position
import model.Team
import java.io.File

object CsvParser {
    fun getPlayers(): MutableList<Player> {
        val players: MutableList<Player> = mutableListOf()
        val fileName = "src/main/resources/fakePlayers.csv"
        var firstLine = true

        File(fileName).forEachLine {
            if (firstLine) {
                firstLine = false
            } else {
                val line = it.split(";")

                val player = Player(
                    name = line[0],
                    team = Team(line[1], line[2]),
                    position = when(line[3]) {
                        "MIDFIELD" -> Position.MIDFIELD
                        "DEFENDER" -> Position.DEFENDER
                        "FORWARD" -> Position.FORWARD
                        "GOALKEEPER" -> Position.GOALKEEPER
                        else -> Position.NOT_FOUND_POSITION
                    } ,
                    nationality = line[4],
                    agency = if(line[5] == "") null else line[5],
                    transferCost = line[6].toInt(),
                    participants = line[7].toInt(),
                    goals = line[8].toInt(),
                    assists = line[9].toInt(),
                    yellowCards = line[10].toInt(),
                    redCards = line[11].toInt()
                )
                players.add(player)
            }
        }

        return players
    }
}