import graph.GraphImpl
import parser.CsvParser
import resolver.ResolverImpl

fun main() {
    val players = CsvParser.getPlayers()
    val resolver = ResolverImpl(players)

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим числом удалений на одного игрока: ${resolver.getTheRudestTeam()}")

    val graphData = resolver.getForwardFromTransferCost()
    GraphImpl().draw(graphData)
}