package parser

import model.FootballPosition
import model.Player
import model.PlayerStats
import model.Team
import java.io.File
import java.util.Scanner

object CsvParser {
    private var players: MutableList<Player> = mutableListOf()

    var Players: List<Player> = players

    fun init(path: String) {
        val file = File(path)
        val scanner = Scanner(file)
        scanner.nextLine()
        while (scanner.hasNextLine()) {
            addPlayerByLine(scanner.nextLine())
        }
    }

    private fun addPlayerByLine(line: String) {
        val fields: List<String> = line.split(";")
        val player = Player(
            Name = fields[0],
            Team = Team(
                Name = fields[1],
                City = fields[2]),
            Position = FootballPosition.valueOf(fields[3]),
            Nationality = fields[4],
            Agency = fields[5],
            TransferCost = fields[6].toLong(),
            Stats = PlayerStats(
                Participations = fields[7].toInt(),
                Goals = fields[8].toInt(),
                Assists = fields[9].toInt(),
                YellowCards = fields[10].toInt(),
                RedCards = fields[11].toInt()
            )
        )

        players.add(player)
    }
}