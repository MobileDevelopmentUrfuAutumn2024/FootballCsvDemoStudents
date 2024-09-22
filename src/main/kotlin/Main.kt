import model.Player
import model.Team
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import parser.CsvParser
import resolver.Resolver
import translator.ENToRUDictionaryTranslator
import java.io.File
import java.io.FileInputStream
import javax.swing.JFrame


fun main(args: Array<String>) {
    val parser = CsvParser()

    val players = parser.parse(
        FileInputStream(File("D:\\projects\\FootballCsvDemoStudents\\src\\main\\resources\\fakePlayers.csv")),
        Player::class
    )

    for (player in players) {
        player.team.players.add(player)
    }

    val teams: List<Team> = players.map { player: Player -> player.team  }.distinct()

    val resolver: Resolver = Resolver(players, teams, ENToRUDictionaryTranslator())

    println("getBestScorerDefender: " + resolver.getBestScorerDefender())
    println("getCountWithoutAgency: " + resolver.getCountWithoutAgency())
    println("getTheExpensiveGermanPlayerPosition: " + resolver.getTheExpensiveGermanPlayerPosition())
    println("getTheRudestTeam: " + resolver.getTheRudestTeam())


    val dataset: DefaultCategoryDataset = DefaultCategoryDataset()
    val topTeams = resolver.getTopTeamsByTransferCost()
        .forEach { team -> dataset.addValue(team.second, "Стоимость", team.first.name) }



    val chart: JFreeChart = ChartFactory.createBarChart(
        "топ-10 команд с наивысшей суммарной трансферной стоимостью",
        "Команды",
        "Стоимость",
        dataset,
        PlotOrientation.HORIZONTAL,
        true,
        true,
        false
    )

    val chartPanel = ChartPanel(chart)
    val frame = JFrame()
    frame.setSize(800, 600)
    frame.contentPane = chartPanel
    frame.setLocationRelativeTo(null)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.isVisible = true
}