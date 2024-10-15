package parser

import model.Player
import model.Position
import model.Team
import java.io.File
import org.yaml.snakeyaml.scanner.Scanner
import kotlin.io.path.Path

object CsvParser {
    public fun parse(path: String): List<Player> {
        return File(path).readLines()
                         .drop(1)
                         .map { parsePlayerFromString(it) }
    }

    private fun parsePlayerFromString(playerString: String): Player {
        val playerParts: List<String> = playerString.split(';')

        return  Player(
                name               = playerParts[0],
                team               = Team(name = playerParts[1], city = playerParts[2]),
                position           = Position.entries.firstOrNull { it.name == playerParts[3] },
                nation             = playerParts[4 ],
                agency             = playerParts[5 ],
                transferCost       = playerParts[6 ].toIntOrNull().orZero(),
                participationCount = playerParts[7 ].toIntOrNull().orZero(),
                goalsCount         = playerParts[8 ].toIntOrNull().orZero(),
                assistsCount       = playerParts[9 ].toIntOrNull().orZero(),
                yellowCardsCount   = playerParts[10].toIntOrNull().orZero(),
                redCardsCount      = playerParts[11].toIntOrNull().orZero()
            );
    }

    private fun Int?.orZero() = this ?: 0
}