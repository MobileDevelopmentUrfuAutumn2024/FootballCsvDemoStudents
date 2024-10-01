package visualization

import model.Position
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.labels.StandardPieSectionLabelGenerator
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import org.jfree.data.general.PieDataset
import java.text.NumberFormat
import javax.swing.JFrame

class JFreeChartVisualizer {

    fun showPositionsChart(title: String, dataMap: Map<Position, Double>) {
        val data = getDataForPieChart(dataMap)
        val chart = ChartFactory.createPieChart(title, data, true, true, false)

        val plot = chart.plot as PiePlot
        plot.isCircular = true
        val percentFormat = NumberFormat.getPercentInstance().apply {
            minimumFractionDigits = 1
            maximumFractionDigits = 2
        }
        plot.labelGenerator = StandardPieSectionLabelGenerator(
            "{0}: {2}",
            NumberFormat.getNumberInstance(),
            percentFormat
        )

        val chartPanel = ChartPanel(chart)
        val frame = JFrame("Data Visualization")
        frame.setSize(1024, 800)
        frame.contentPane = chartPanel
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.isVisible = true
    }

    private fun getDataForPieChart(data: Map<Position, Double>): PieDataset {
        val chartDataSet = DefaultPieDataset()
        for ((position, share) in data) {
            chartDataSet.setValue(position.rusName, share)
        }
        return chartDataSet
    }

}