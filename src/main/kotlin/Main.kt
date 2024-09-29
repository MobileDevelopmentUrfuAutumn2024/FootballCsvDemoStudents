import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.barsH
import resolver.Resolver

fun main(args: Array<String>) {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    println("количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Aвтор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("командa с наибольшим средним числом красных карточек на одного игрока: ${resolver.getTheRudestTeam().name}")

    /** Вариант 2: **/
    val topTeams =
        players.groupBy { it.team.name }.map { (team, players) -> team to players.sumOf { it.transferCost ?: 0 } }
            .sortedByDescending { (_, total) -> total }.take(10)

    plot {
        layout.title = "команды с наивысшей трансферной стоимостью"
        barsH {
            y(topTeams.map { it.first }) { axis.name = "Команды" }
            x(topTeams.map { it.second / 1000000 }) { axis.name = "Стоимость, млн" }
            alpha = 0.75
        }
    }.save("chart.png")
}