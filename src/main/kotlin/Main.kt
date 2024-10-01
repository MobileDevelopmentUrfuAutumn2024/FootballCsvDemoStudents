import parser.CsvParser
import resolver.Resolver
import graph.Drawning

fun main(args: Array<String>) {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    // Основное задание:
    println("Кол-во игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Защитник с наибольшим кол-вом голов и их кол-во: ${resolver.getBestScorerDefender()}")
    println("Позиция самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим кол-вом нарушений на одного игрока: ${resolver.getTheRudestTeam()}")

    // Вариант 2:
    val topTenTeams = players.groupBy { it.team.name }.map { it.key to it.value.sumOf { it.transferCost } }.sortedByDescending { it.second }.take(10)
    val drawning = Drawning(topTenTeams)
    drawning.DrawPie()
    drawning.DrawCategories()
}