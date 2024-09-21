import parser.CsvParser
import resolver.IResolver
import resolver.Resolver
import org.knowm.xchart.PieChartBuilder
import org.knowm.xchart.SwingWrapper


fun main(args: Array<String>) {
    val players = CsvParser.readCSV("src/main/resources/fakePlayers.csv")
    val resolver: IResolver = Resolver(players)
    val chart = PieChartBuilder().width(800).height(600).title("Круговая диаграмма Ларина ИВ").build()



    println(
        "Количество игроков, чьи интересы не представляет агенство: " +
                "${resolver.getCountWithoutAgency()}"
    )
    println(
        "Наибольшее число голов - ${resolver.getBestScorerDefender().second} - из числа " +
                "защитников забил: ${resolver.getBestScorerDefender().first}"
    )
    println(
        "Самый дорогой немецкий игрок стоит на позиции: " +
                resolver.getTheExpensiveGermanPlayerPosition()
    )
    println(
        "Команда с наибольшим средним числом красных карточек на одного игрока - " +
                "${resolver.getTheRudestTeam().name} из города ${resolver.getTheRudestTeam().city}"
    )

    val positions = mutableMapOf<String, Int>()
    for (player in players) {
        if (!positions.containsKey(player.position.russianName)) {
            positions[player.position.russianName] = 1
        } else {

            positions[player.position.russianName] = positions[player.position.russianName]!! + 1
        }
    }

    for (position in positions) {
        chart.addSeries(position.key, position.value)
    }

    SwingWrapper(chart).displayChart()
}