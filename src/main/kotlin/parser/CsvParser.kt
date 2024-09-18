package parser

import model.Player
import model.getPosition
import java.io.File
import java.util.*

/**
 * Парсер CSV
 */
object CsvParser {

    private val players: MutableList<Player> = mutableListOf()

    /**
     * парсинг
     * @param path путь к файлу
     * @return список игроков
     */
    fun parse(path: String): List<Player> {
        val file = File(path)
        val scanner = Scanner(file)

        scanner.nextLine()
        while (scanner.hasNextLine()) {
            players.add(
                createPlayer(scanner.nextLine())
            )
        }

        return players
    }

    /**
     * создание модели игрока
     * @param line строка данных
     * @return игрок
     */
    private fun createPlayer(line: String): Player {
        val data = line.split(";")
        return Player(
            name = data[0],
            team = data[1],
            city = data[2],
            position = getPosition(data[3]),
            nationality = data[4],
            agency = data[5],
            transferCost = data[6].toLong(),
            participations = data[7].toInt(),
            goals = data[8].toInt(),
            assists = data[9].toInt(),
            yellowCard = data[10].toInt(),
            redCards = data[11].toInt()
        )
    }
}