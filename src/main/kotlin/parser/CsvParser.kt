package parser

import model.Player
import model.Team
import java.io.File

object CsvParser: ICsvParser<Player>{

    override fun parse(filename: String): List<Player> {
        return File(filename).readLines().drop(1).map{ parseLine(it) }
    }

    override fun parseLine(line: String): Player{
        val fields = line.split(";")
        return Player(
            name = fields[0],
            team = Team(fields[1], fields[2]),
            position = fields[3],
            nationality = fields[4],
            agency = fields[5],
            transferCost = fields[6].toInt(),
            participations = fields[7].toInt(),
            goals = fields[8].toInt(),
            assists = fields[9].toInt(),
            yellowCards = fields[10].toInt(),
            redCards = fields[11].toInt()
        )
    }
}