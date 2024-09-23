import parser.CsvParser
import resolver.Resolver
import visualization.Graph

fun main() {
    val players = CsvParser.parsePlayers("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)
    val (name, maxGoals) = resolver.getBestScorerDefender().first()

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников: $name")
    println("Количество голов: $maxGoals")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим средним числом красных карточек на одного игрока: ${resolver.getTheRudestTeam().name}")

    Graph().graphGeneration(players)
}