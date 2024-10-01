package parser

import model.Player
import model.Position
import model.Team
import java.io.File

object CsvParser{
    private val playerList: MutableList<Player> = mutableListOf()

    fun parser(): MutableList<Player>{
        val xmlFile = File("src/main/resources/fakePlayers.csv").readText().split("\r")
        playerListCreate(xmlFile)
        return playerList
    }

    private fun playerListCreate(list: List<String>){
        for(i in 1..<list.size-1){
            this.playerList.add(playerConstrctor(list[i]))
        }
    }

    private fun playerConstrctor(string: String) : Player {
        val data = string.split(";")
        val team = Team(data[1], data[2])
        val position = Position.entries.find { it.name == data[3] }
            ?: Position.Other
        return Player(
            data[0],
            team,
            position,
            data[4],
            data[5],
            data[6],
            data[7].toInt(),
            data[8].toInt(),
            data[9].toInt(),
            data[10].toInt(),
            data[11].toInt())
    }
}