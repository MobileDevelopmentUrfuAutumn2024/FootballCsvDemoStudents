import parser.CsvParser
import plot.visualizePlayersByCountry
import resolver.Resolver

fun main(args: Array<String>) {

    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")

    println("${Resolver(players).getCountWithoutAgency()} - Выведите количество игроков, интересы которых не представляет агенство.")
    println("${Resolver(players).getBestScorerDefender()} - Выведите автора наибольшего числа голов из числа защитников и их количество.")
    println("${Resolver(players).getTheExpensiveGermanPlayerPosition()} - Выведите русское название позиции самого дорогого немецкого игрока.")
    println("${Resolver(players).getTheRudestTeam()} - Выберите команду с наибольшим числом удалений на одного игрока.")

    visualizePlayersByCountry(players)
}