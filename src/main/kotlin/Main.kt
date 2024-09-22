import parser.CsvParser
import resolver.IResolver
import resolver.Resolver

const val PATH = "src/main/resources/fakePlayers.csv"

fun main() {
    val players = CsvParser.parse(PATH)
    val resolver: IResolver = Resolver(players)

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим средним числом красных карточек на одного игрока: ${resolver.getTheRudestTeam()}")

    resolver.getDependenceOfGoalsOnCoastForForwards()
}