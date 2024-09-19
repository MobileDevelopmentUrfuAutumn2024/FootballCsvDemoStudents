import parser.CsvParser
import resolver.Resolver
import ui.ShareOfPlayersByCountryFrame


fun main() {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    println(Resolver.getCountWithoutAgency(players))
    println(Resolver.getBestScorerDefender(players))
    println(Resolver.getTheExpensiveGermanPlayerPosition(players))
    println(Resolver.getTheRudestTeam(players))

    ShareOfPlayersByCountryFrame.show(players)
}