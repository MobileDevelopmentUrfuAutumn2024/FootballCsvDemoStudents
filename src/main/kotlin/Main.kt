import adapter.JFreeChartGenerator
import parser.CsvParser
import resolver.Resolver

fun main(args: Array<String>) {

    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    println("1. Количество игроков, интересы которых не представляет агенство: " + resolver.getCountWithoutAgency())
    println("2. Автор наибольшего числа голов из числа защитников и их количество: " + resolver.getBestScorerDefender())
    println("3. Русское название позиции самого дорогого немецкого игрока: " + resolver.getTheExpensiveGermanPlayerPosition())
    println("4. Команда с наибольшим средним числом красных карточек на одного игрока: " + resolver.getTheRudestTeam().name)

    JFreeChartGenerator.generatePlayersDistributionByCountryChart(players)

}