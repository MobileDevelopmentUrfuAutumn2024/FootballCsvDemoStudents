package parser

import model.Player
import model.Team
import java.io.File

object CsvParser {
    fun parse(filePath: String): List<Player> {
        return File(filePath)
            .readLines()
            .drop(1)
            .map { it.toPlayer() }
    }

    fun String.toPlayer(): Player {
        val columns: List<String> = split(';')

        return Player(
            name = columns[0],
            team = Team(columns[1], columns[2]),
            position = enumValueOf(columns[3]),
            nationality = columns[4],
            agency = columns[5].ifBlank { null },
            transferCost = columns[6].toLong(),
            participationCount = columns[7].toInt(),
            goalsCount = columns[8].toInt(),
            assistsCount = columns[9].toInt(),
            yellowCardsCount = columns[10].toInt(),
            redCardsCount = columns[11].toInt()
        )
    }
}