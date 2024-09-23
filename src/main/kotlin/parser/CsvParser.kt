package parser
import model.Player
import model.Position
import model.Team
import java.io.File
import java.util.Scanner

object CsvParser {

    private var players: MutableList<Player> = mutableListOf()

    fun parse(path: String): List<Player> {
        val file = File(path)
        val scanner = Scanner(file)

        scanner.nextLine()

        while (scanner.hasNextLine()) {
            parsePlayerFromString(scanner.nextLine())

        }

        return players
    }

    private fun parsePlayerFromString(playerString:String) {
        val playerParse = playerString.split(";")

        players.add(
            Player(
                name = playerParse[0],
                team = Team(name = playerParse[1], city = playerParse[2]),
                position = Position.getByValue(playerParse[3]),
                agency = playerParse[5],
                nationality = playerParse[4],
                transferCost = playerParse[6].toIntOrNull().onZero(),
                participationCount =  playerParse[7].toIntOrNull().onZero(),
                goalsCount = playerParse[8].toIntOrNull().onZero(),
                assistsCount = playerParse[9].toIntOrNull().onZero(),
                yellowCardsCount = playerParse[10].toIntOrNull().onZero(),
                redCardsCount = playerParse[11].toIntOrNull().onZero()
            )
        )
    }
    private fun Int?.onZero(): Int = this ?: 0
}
