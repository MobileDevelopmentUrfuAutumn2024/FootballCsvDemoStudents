import graph.GraphDrawer
import parser.CsvParser
import resolver.IResolver
import resolver.Resolver

fun main(args: Array<String>) {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver: IResolver = Resolver(players)

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим числом удалений на одного игрока: ${resolver.getTheRudestTeam()}")

    GraphDrawer.draw(resolver.getTeamsTotalTransferCosts(10))
}