import parser.CsvParser
import resolver.Resolver
import ui.ShareOfPlayersByCountryFrame


fun main() {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    players.take(5).forEach { println(it) }
    val resolver = Resolver(players)
    println(resolver.getCountWithoutAgency())
    println(resolver.getBestScorerDefender())
    println(resolver.getTheExpensiveGermanPlayerPosition())
    println(resolver.getTheRudestTeam())

    ShareOfPlayersByCountryFrame.show(players)
}