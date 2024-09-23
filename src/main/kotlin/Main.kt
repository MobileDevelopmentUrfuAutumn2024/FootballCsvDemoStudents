import parser.CsvParser
import resolver.Resolver
import main.buildAndSaveChart

fun main(args: Array<String>) {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    println("Количество игроков без агенства: ${resolver.getCountWithoutAgency()}")
    println("Лучший защитник-бомбардир: ${resolver.getBestScorerDefender()}")
    println("Самый дорогой немецкий игрок, его позиция: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Самая грубая команда: ${resolver.getTheRudestTeam()}")

    // Вызов функции для построения и сохранения графика
    buildAndSaveChart(players)
}
