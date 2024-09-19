package parser

import com.sun.jdi.Value
import model.Player
import model.Position
import model.Team
import java.io.File

object CsvParser{
    private var playerList: MutableList<Player> = mutableListOf()

    fun parser(){
        val xmlFile = File("src/main/resources/fakePlayers.csv").readText().split("\n")
        playerListCreate(xmlFile)
        print(playerList[0])
    }

    private fun playerListCreate(list: List<String>){
        for(i in 1..list.size){
            this.playerList.add(playerConstrctor(list[i]))
        }
    }

    private fun playerConstrctor(string: String): Player{
        val data = string.split(";")
        var position: Position
        val team = Team(data[1], data[2])
        try {
            position = Position.valueOf(data[3])
        }
        finally {
            position = Position.Other
        }
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