import model.Player
import org.jfree.chart.ChartPanel
import parser.CsvParser
import resolver.IResolver
import resolver.Resolver
import visualizer.Drawer
import javax.swing.JFrame

fun main(args: Array<String>) {

    val players: List<Player> = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver: IResolver = Resolver(players)

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.getCountWithoutAgency()}")
    println("Автор наибольшего числа голов из числа защитников и их количество: ${resolver.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда с наибольшим числом удалений на одного игрока: ${resolver.getTheRudestTeam()}")

    val drawer: Drawer = Drawer()

    val dataset = drawer.createDataset(players)
    val chart = drawer.createChart(dataset)
    val chartPanel = ChartPanel(chart)
    chartPanel.isMouseWheelEnabled = true
    val frame = JFrame("Football Players Chart")
    frame.contentPane = chartPanel
    frame.pack()
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.isVisible = true

}