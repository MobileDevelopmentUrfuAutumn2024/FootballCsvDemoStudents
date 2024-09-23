package parser


import model.Player
import model.Position
import model.Team
import java.io.File

object CsvParser: ICsvParser<Player>{

    override fun parse(filename: String): List<Player> {
        return File(filename).readLines().drop(1).map{ parseLine(it) }
    }

    override fun parseLine(line: String): Player{
        val fields = line.split(";")
        val position = Position.entries.firstOrNull {it.name == fields[3]}
        return Player(
            name = fields[0],
            team = Team(fields[1], fields[2]),
            position = position,
            nationality = fields[4],
            agency = fields[5],
            transferCost = fields[6].toIntOrNull(),
            participations = fields[7].toIntOrNull(),
            goals = fields[8].toIntOrNull(),
            assists = fields[9].toIntOrNull(),
            yellowCards = fields[10].toIntOrNull(),
            redCards = fields[11].toIntOrNull()
        )
    }


}