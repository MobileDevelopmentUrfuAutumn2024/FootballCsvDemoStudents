package parser

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import model.Player
import model.Team
import model.enums.Position
import java.io.FileReader

object CsvParser {
    fun readCsvWithPlayers(path: String, separator: Char = ';'): Array<Player> {
        val parser = CSVParserBuilder()
            .withSeparator(separator)
            .build()
        return CSVReaderBuilder(FileReader(path))
            .withCSVParser(parser)
            .withSkipLines(1)
            .build()
            .map { it -> Player(
                name = it[0],
                team = Team (
                    name = it[1],
                    town = it[2]
                ),
                position = Position.valueOf(it[3]),
                nationality = it[4],
                agency = it[5],
                transferCost = it[6].toInt(),
                participations = it[7].toInt(),
                goals = it[8].toInt(),
                assists = it[9].toInt(),
                yellowCards = it[10].toInt(),
                redCards = it[11].toInt()
            ) }
            .toTypedArray()
    }
}