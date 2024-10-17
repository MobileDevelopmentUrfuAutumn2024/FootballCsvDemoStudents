import graph.GraphDrawer
import parser.CsvParser
import resolver.IResolver
import resolver.Resolver

fun main(args: Array<String>) {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver: IResolver = Resolver(players)

    println("Количество игроков, без агенства: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников и количество голов: ${resolver.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим средним числом красных карточек на одного игрока: ${resolver.getTheRudestTeam()}")

    GraphDrawer.draw(resolver.getTeamsTotalTransferCosts(10))
}