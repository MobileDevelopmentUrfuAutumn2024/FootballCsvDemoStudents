import parser.CsvParser
import resolver.Resolver

fun main() {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    val visualizer = PlayerCountryVisualizer(players)
    visualizer.showPlayerDistributionByCountry()

    println(resolver.getCountWithoutAgency())
    println(resolver.getBestScorerDefender())
    println(resolver.getTheExpensiveGermanPlayerPosition())
    println(resolver.getTheRudestTeam())
}