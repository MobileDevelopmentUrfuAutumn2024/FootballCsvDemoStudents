import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import org.jfree.chart.labels.StandardPieSectionLabelGenerator
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import java.io.File
import java.text.DecimalFormat

class PlayerCountryVisualizer(private val players: List<Player>) {

    fun showPlayerDistributionByCountry() {

        val countryDistribution: Map<String, Int> = players.groupBy { it.nationality }
            .mapValues { (_, playersList) -> playersList.size }

        val dataset = DefaultPieDataset<String>()
        countryDistribution.forEach { (country, count) ->
            dataset.setValue(country, count)
        }

        val chart = ChartFactory.createPieChart(
            "Player Distribution by Country",
            dataset,
            true,
            true,
            false
        )

        val plot = chart.plot as PiePlot<*>
        plot.isCircular = true
        plot.setSectionPaint("Germany", java.awt.Color.BLUE)


        val labelGenerator = StandardPieSectionLabelGenerator(
            "{0} : {1} ({2})",
            DecimalFormat("0"),
            DecimalFormat("0.00%")
        )
        plot.labelGenerator = labelGenerator



        val chartFile = File("D:/PlayerDistributionByCountry.png")
        ChartUtils.saveChartAsPNG(chartFile, chart, 800, 600)
        println("Chart saved as ${chartFile.absolutePath}")
    }
}
