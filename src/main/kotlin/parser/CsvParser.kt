package parser

import model.Player
import model.Position
import model.Team
import java.io.File

object PlayersCsvParser {

    /** Функция для парсинга файла по пути
     * @param path путь до файла
     * @return Список игроков
     * */
    fun parse(path: String): List<Player> {
        val linesArray = File(path).readLines().drop(1)

        val players = linesArray.map { parsePlayerLine(it) }

        return players
    }

    /** Функция для парсинга строки в экземпляр класса Player
     * @param line строка из файла
     * @return Экзмепляр класса Player
     * */
    private fun parsePlayerLine(line: String): Player {
        val playerParts = line.split(";")

        return Player(
            name = playerParts[0],
            team = Team(name = playerParts[1], town = playerParts[2]),
            position = Position.entries.find { it.name == playerParts[3] } ?: Position.DEFAULT,
            nationality = playerParts[4],
            agency = playerParts[5],
            transferCost = normalizeInt(playerParts[6]),
            participations = normalizeInt(playerParts[7]),
            goals = normalizeInt(playerParts[8]),
            assists = normalizeInt(playerParts[9]),
            yellowCards = normalizeInt(playerParts[10]),
            redCards = normalizeInt(playerParts[11]),
        )
    }

    /** Функция для приведения строки в число
     * @param str строка
     * @return число
     * */
    private fun normalizeInt(str: String): Int {
        return str.toIntOrNull() ?: 0
    }
}