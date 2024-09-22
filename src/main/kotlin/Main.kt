// Анфилофьев Денис
import model.Player
import parser.CsvParser
import resolver.Resolver

fun main() {
    val fileName = "src/main/resources/fakePlayers.csv"

    // Парсинг файла CSV
    val players: List<Player> = CsvParser.parse(fileName)

    // Создаем объект Resolver
    val resolver = Resolver()

    // 1. Количество игроков, не имеющих агентства
    println("Количество игроков, интересы которых не представляет агенство: ${resolver.countPlayersWithoutAgency(players)}")

    // 2. Автор наибольшего числа голов из числа защитников и количество голов
    val topScorerDefender = resolver.topScorerAmongDefenders(players)
    println("Автор наибольшего числа голов из числа защитников и количество голов: ${topScorerDefender.first} с ${topScorerDefender.second} голами")

    fun getRussianPosition(position: String): String {
        return when (position) {
            "GOALKEEPER" -> "Вратарь"
            "DEFENDER" -> "Защитник"
            "MIDFIELDER" -> "Полузащитник"
            "FORWARD" -> "Нападающий"
            else -> "Неизвестная позиция"
        }
    }
    // 3. Самый дорогой немецкий игрок
    val mostExpensiveGerman = players.filter { it.nationality == "Germany" }.maxByOrNull { it.transferCost }
    println("Позиция самого дорогого немецкого игрока: ${getRussianPosition(mostExpensiveGerman?.position ?: "")}")

    // 4. Команда с наибольшим числом красных карточек
    println("Команда с наибольшим средним числом красных карточек на одного игрока: ${resolver.teamWithMostRedCards(players)}")

    // 5. Визуализация голов против трансферной стоимости
    resolver.visualizeGoalsVsTransferCost(players)
}