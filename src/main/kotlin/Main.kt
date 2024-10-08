import model.Player
import model.Team
import resolver.IResolver
import resolver.Resolver
import java.io.File
import java.awt.Dimension
import javax.swing.JFrame
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.ChartUtils
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection

fun parsePlayers(filePath: String): List<Player> {
    return File(filePath).readLines()
        .drop(1)
        .map { line ->
            val data = line.split(";")
            Player(
                name = data[0],
                team = data[1],
                city = data[2],
                position = data[3],
                nationality = data[4],
                agency = if (data[5].isNotEmpty()) data[5] else null,
                transferValue = data[6].toInt(),
                appearances = data[7].toInt(),
                goals = data[8].toInt(),
                assists = data[9].toInt(),
                yellowCards = data[10].toInt(),
                redCards = data[11].toInt()
            )
        }
}

fun createTeams(players: List<Player>): List<Team> {
    val teamMap = mutableMapOf<String, MutableList<Player>>()

    for (player in players) {
        teamMap.getOrPut(player.team) { mutableListOf() }.add(player)
    }

    return teamMap.map { (teamName, players) ->
        Team(name = teamName, city = players.first().city, players = players)
    }
}

fun createDiagram(players: List<Player>) {
    val series = XYSeries("Нападающие")

    players.filter { it.position == "FORWARD" }.forEach { forward ->
        series.add(forward.transferValue.toDouble(), forward.goals.toDouble())
    }

    val dataset = XYSeriesCollection()
    dataset.addSeries(series)

    val chart = ChartFactory.createXYLineChart(
        "Зависимость количества забитых голов от трансферной стоимости для нападающих",
        "Трансферная стоимость",
        "Количество голов",
        dataset,
        PlotOrientation.VERTICAL,
        true, true, false
    )

    ChartUtils.saveChartAsPNG(File("goals_vs_transfer.png"), chart, 800, 600)

    val frame = JFrame("График голов vs трансферной стоимости")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.contentPane.add(ChartPanel(chart).apply { preferredSize = Dimension(800, 600) })
    frame.pack()
    frame.isVisible = true
}

fun main() {
    val filePath = "src/main/resources/fakePlayers.csv"

    val players = parsePlayers(filePath)

    val teams = createTeams(players)

    val resolver: IResolver = Resolver()

    println("Количество игроков без агентства: ${resolver.getCountWithoutAgency(players)}")

    val (bestDefenderName, bestDefenderGoals) = resolver.getBestScorerDefender(players)
    println("Лучший защитник: $bestDefenderName, количество голов: $bestDefenderGoals")

    println("Самый дорогой немецкий игрок на позиции: ${resolver.getTheExpensiveGermanPlayerPosition(players)}")

    val rudestTeam = resolver.getTheRudestTeam(teams)
    println("Команда с наибольшим числом удалений на одного игрока: ${rudestTeam.name}")

    createDiagram(players)
}
