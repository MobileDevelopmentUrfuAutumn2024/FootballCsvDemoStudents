import model.Player
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.layout
import org.jetbrains.kotlinx.kandy.letsplot.layers.pie
import org.jetbrains.kotlinx.kandy.letsplot.style.Style
import parser.toPlayers
import resolver.Resolver

fun main() {
    val players = getResource("fakePlayers.csv")
        ?.toPlayers()
        ?: return println("No resource file found.")
    val resolver = Resolver(players)

    println("""
        Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}
        Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}
        Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}
        Команда с наибольшим средним числом красных карточек на одного игрока: ${resolver.getTheRudestTeam()}
    """.trimIndent())
    /*
    Количество игроков, интересы которых не представляет агенство: 26
    Автор наибольшего числа голов из числа защитников и их количество: (Sidney Durgan, 29)
    Русское название позиции самого дорогого немецкого игрока: нападающий
    Команда с наибольшим средним числом красных карточек на одного игрока: Team(name=Wisconsin prophets, city=Vermont)
     */
    plot(players)
}

private fun plot(players: List<Player>) {
    val frame = dataFrameOf(
        "position" to Player.Position.entries,
        "fraction" to Player.Position.entries.map { position ->
            players.count { it.position == position } / players.size.toDouble()
        },
    )
    frame.plot {
        pie {
            slice("fraction")
            fillColor("position")
            size = 25.0
        }
        layout {
            title = "Доля игроков каждой позиции от всех игроков"
            style(Style.Void)
        }
    }.save("plot.png")
}
