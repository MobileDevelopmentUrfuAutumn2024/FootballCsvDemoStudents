import model.Player
import parser.CsvParser
import resolver.IResolver
import resolver.Resolver
import visualize.Draw

fun main(args: Array<String>) {
    val players : List<Player> = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver : IResolver = Resolver(players)
    println("количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("автор наибольшего числа голов из числа защитников и их количество ${resolver.getBestScorerDefender()}")
    println("русское название позиции самого дорогого немецкого игрока" +
            " ${resolver.getTheExpensiveGermanPlayerPosition()} ")
    println("команда с наибольшим числом удалений на одного игрока ${resolver.getTheRudestTeam()}")
    Draw().draw(resolver.getCountPlayersByPosition())
    print("Yeah rock!")
}