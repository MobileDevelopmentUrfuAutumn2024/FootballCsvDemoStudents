import model.PlayerPosition
import parser.CsvParser
import resolver.Resolver
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.util.color.Color

fun main(args: Array<String>) {
    val teams = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(teams)

    //Main task
    val countWithoutAgency = resolver.getCountWithoutAgency()
    val bestScorerDefender = resolver.getBestScorerDefender()
    val theExpensiveGermanPlayerPosition = resolver.getTheExpensiveGermanPlayerPosition()
    val theRudestTeam = resolver.getTheRudestTeam()

    println("Количество игроков, интересы которых не представляет агенство: $countWithoutAgency")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${bestScorerDefender.first} ${bestScorerDefender.second}")
    println("Русское название позиции самого дорогого немецкого игрока: $theExpensiveGermanPlayerPosition")
    println("Команда с наибольшим числом удалений на одного игрока: ${theRudestTeam.name}")

    //Additional task
    val forwards =
        teams.flatMap { it.players }
            .filter { it.personData.position == PlayerPosition.FORWARD }
    val dataFrame = dataFrameOf(
        "Transfer Cost" to forwards.map { it.transferData.cost.toFloat() },
        "Goals Amount" to forwards.map { it.statisticsData.goals }
    )

    dataFrame.plot {
        line {
            x("Transfer Cost")
            y("Goals Amount")
            width = 3.0
            color = Color.RED
        }

    }.save("V3.png")
}