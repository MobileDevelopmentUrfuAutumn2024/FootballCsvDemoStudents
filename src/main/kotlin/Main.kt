import model.Position
import resolver.IResolver
import resolver.Resolver

fun main(args: Array<String>) {
    val table = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver: IResolver = Resolver(table)
    println(resolver.getCountWithoutAgency())
    println(resolver.getBestScorerDefender())
    println(resolver.getTheExpensiveGermanPlayerPosition())
    println(resolver.getTheRudestTeam())
    print("Yeah rock!")
}