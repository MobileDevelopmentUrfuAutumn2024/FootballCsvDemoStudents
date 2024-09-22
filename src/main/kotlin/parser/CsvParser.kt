package parser

import model.Player
import model.PositionsRu
import model.Team
import java.io.File
import java.util.Scanner

object CsvParser {
    fun Parse(): MutableList<Player>{
        var players: MutableList<Player> = mutableListOf()
        val check = Scanner(File("src/main/resources/fakePlayers.csv"))
        check.nextLine()
        while (check.hasNextLine()){
            val dataPlayer = check.nextLine().split(";")
            val i = Player(dataPlayer[0],
                Team(dataPlayer[1],
                    dataPlayer[2]),
                PositionsRu.valueOf(dataPlayer[3]),
                dataPlayer[4],
                dataPlayer[5],
                dataPlayer[6].toInt(),
                dataPlayer[7].toInt(),
                dataPlayer[8].toInt(),
                dataPlayer[9].toInt(),
                dataPlayer[10].toInt(),
                dataPlayer[11].toInt()
            )
            players.add(i)
        }
        return players
    }
}