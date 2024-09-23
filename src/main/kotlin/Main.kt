import parser.PlayersCsvParser
import resolver.Resolver

fun main(args: Array<String>) {
    val players = PlayersCsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    val countWithoutAgency = resolver.getCountWithoutAgency()
    val bestDefender = resolver.getBestScorerDefender()
    val expensivePlayer = resolver.getTheExpensiveGermanPlayerPosition()
    val rudestTeam = resolver.getTheRudestTeam()

    println("Количество игроков, интересы которых не представляет агенство: $countWithoutAgency")
    println("Автор наибольшего числа голов из числа защитников: ${bestDefender.first}, их количество: ${bestDefender.second}")
    println("Название позиции самого дорогого немецкого игрока: $expensivePlayer")
    println("Команда с наибольшим числом удалений на одного игрока: ${rudestTeam.name} из города ${rudestTeam.town}")
}