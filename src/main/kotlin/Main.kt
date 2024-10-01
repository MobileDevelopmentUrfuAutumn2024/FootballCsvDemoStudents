import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import parser.CsvParser
import resolver.Resolver
import model.FootballPosition
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars

fun main(args: Array<String>) {
    CsvParser.init("src/main/resources/fakePlayers.csv");
    val resolver = Resolver(CsvParser.Players)
    println(
        "Количество игроков, интересы которых не представляет агенство:" +
                " ${resolver.getCountWithoutAgency()}"
    )
    println(
        "Автор наибольшего числа голов из числа защитников и их количество.:" +
                " ${resolver.getBestScorerDefender()}"
    )
    println(
        "Русское название позиции самого дорогого немецкого игрока.:" +
                " ${resolver.getTheExpensiveGermanPlayerPosition()}"
    )
    println(
        "Команда с наибольшим средним числом красных карточек на одного игрока.:" +
                " ${resolver.getTheRudestTeam()}"
    )

    val countPlayers = CsvParser.Players.size
    val playersByPosition =
        CsvParser.Players.groupBy { it.Position }


    val percentage = playersByPosition.map { (_, players) ->
        (players.size.toDouble() / countPlayers) * 100
    }

    dataFrameOf(
        "Position" to FootballPosition.entries,
        "Percentage" to percentage
    ).plot {
        bars {
            x("Position")
            y("Percentage") {
                axis.name = "Percentage"
            }
        }
        layout.title = "Position ratio"
    }.save("position_ratio.png")
}