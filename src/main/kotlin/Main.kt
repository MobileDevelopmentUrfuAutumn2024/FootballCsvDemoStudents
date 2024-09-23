import model.Player
import parser.CsvParser
import resolver.IResolver
import resolver.Resolver
import visualization.Visualizer

fun main(args: Array<String>) {
    val players : List<Player> = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver : IResolver = Resolver(players)

    println("1. Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("2. Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("3. Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("4. Команду с наибольшим средним числом красных карточек на одного игрока: ${resolver.getTheRudestTeam()}")
    Visualizer().draw(resolver.getPercentagePositionOfPlayers())
}