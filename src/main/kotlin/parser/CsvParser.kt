package parser

import model.Player
import java.io.File
import java.io.FileNotFoundException
import java.util.Scanner

object CsvParser {
    fun parsePlayers(filePath: String): List<Player> {
        val playersList = mutableListOf<Player>()

        try {
            val file = File(filePath)
            val scanner = Scanner(file)

            if (scanner.hasNextLine()) {
                scanner.nextLine()
            }

            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                val columns = line.split(";")
                if (columns.size < 12) {
                    println("Нет данных $line")
                } else {
                    try {
                        val player = Player(
                            name = columns[0],
                            team = columns[1],
                            city = columns[2],
                            position = columns[3],
                            nationality = columns[4],
                            agency = columns[5],
                            transferCost = columns[6].toInt(),
                            participations = columns[7].toInt(),
                            goals = columns[8].toInt(),
                            assists = columns[9].toInt(),
                            yellowCards = columns[10].toInt(),
                            redCards = columns[11].toInt()
                        )
                        playersList.add(player)
                    } catch (e: NumberFormatException) {
                        println("Ошибка преобразования $line")
                    }
                }
            }

            scanner.close()
        } catch (e: FileNotFoundException) {
            println("Файла нет: $filePath")
        }

        return playersList
    }
}