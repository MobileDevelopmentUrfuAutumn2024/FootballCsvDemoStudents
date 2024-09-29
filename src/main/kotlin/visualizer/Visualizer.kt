package visualizer

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.labels.StandardPieSectionLabelGenerator
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import org.jfree.data.general.PieDataset
import java.awt.Dimension
import java.text.DecimalFormat
import javax.swing.JFrame

/**
 * Графический визуализатор
 */
object Visualizer {

    /**
     * Отобразить диаграмму на основе данных в виде карты
     *
     * @param title     заголовок диаграммы
     * @param dataSet   данные для диаграммы
     */
    fun showPieChartFromDataset(title: String, dataSet: Map<String, Double>) {
        val chartDataSet = createChartDataset(dataSet)
        val chart = createChart(title, chartDataSet)
        showChart(chart)
    }

    private fun createChartDataset(dataSet: Map<String, Double>): PieDataset {
        val chartDataSet = DefaultPieDataset()
        for ((country, share) in dataSet) {
            chartDataSet.setValue(country, share)
        }
        return chartDataSet
    }

    private fun createChart(title: String, dataset: PieDataset): JFreeChart {
        val chart = ChartFactory.createPieChart(title, dataset, true, true, false)
        val plot = chart.plot as PiePlot
        plot.labelGenerator = StandardPieSectionLabelGenerator(
            "{0}: ({2})", DecimalFormat("0"), DecimalFormat("0.0%")
        )
        return chart
    }

    private fun showChart(chart: JFreeChart) {
        val frame = JFrame("Player visualization")
        val chartPanel = ChartPanel(chart)
        chartPanel.preferredSize = Dimension(1024, 768)
        frame.contentPane = chartPanel
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.pack()
        frame.isVisible = true
    }

}