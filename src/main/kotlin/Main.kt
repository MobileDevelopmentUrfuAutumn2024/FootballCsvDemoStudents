import resolver.Resolver
import org.jfree.data.general.DefaultPieDataset
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import model.*
import java.io.File

fun main(args: Array<String>) {
    val r = Resolver()
    println(r.getCountWithoutAgency())
    println(r.getBestScorerDefender())
    println(r.getTheExpensiveGermanPlayerPosition())
    println(r.getTheRudestTeam())

    val positionCount = r.players.groupingBy { it.position }.eachCount()

    println(positionCount)

    val dataset = DefaultPieDataset<String>()
    dataset.setValue(PositionsRu.DEFENDER.toString(),
        positionCount[PositionsRu.DEFENDER]!!.toDouble())
    dataset.setValue(PositionsRu.FORWARD.toString(),
        positionCount[PositionsRu.FORWARD]!!.toDouble())
    dataset.setValue(PositionsRu.MIDFIELD.toString(),
        positionCount[PositionsRu.MIDFIELD]!!.toDouble())
    dataset.setValue(PositionsRu.GOALKEEPER.toString(),
        positionCount[PositionsRu.GOALKEEPER]!!.toDouble())

    var chart = ChartFactory.createPieChart(
        "Круговой график",
        dataset,
        true,
        true,
        false
    )

    val file = File("src/main/res.png")
    ChartUtils.saveChartAsPNG(file, chart, 600, 400)
}