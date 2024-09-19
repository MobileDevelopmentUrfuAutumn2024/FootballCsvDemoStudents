import resolver.Resolver

fun main(args: Array<String>) {
    val l = Resolver()
    print(l.getCountWithoutAgency())
    println("${l.getBestScorerDefender().toList()[0]}: ${l.getBestScorerDefender().toList()[1]}")
    println(l.getTheExpensiveGermanPlayerPosition())
    val grudge = l.getTheRudestTeam()
    println(grudge.redCardsNum/grudge.playersCount)
}