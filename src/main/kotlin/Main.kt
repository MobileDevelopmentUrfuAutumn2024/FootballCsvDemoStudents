import model.Player
import parser.CsvParser
import resolver.IResolver
import resolver.Resolver
import visualization.JFreeChartVisualizer

fun main(args: Array<String>) {
    val players: List<Player> = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver: IResolver = Resolver(players)
    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим средним числом красных карточек на одного игрока: ${resolver.getTheRudestTeam()}")

    val visualizer = JFreeChartVisualizer()
    visualizer.showPositionsChart("Визуализация", resolver.getShareOfPlayersByPosition())
}