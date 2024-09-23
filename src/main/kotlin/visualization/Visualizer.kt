package visualization

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.chart.ui.RectangleInsets
import org.jfree.data.general.DefaultPieDataset
import org.jfree.data.general.PieDataset
import java.awt.Dimension


class Visualizer {

    fun draw(data: Map<String, Double>) {
        val dataset = createDataset(data)
        val chart = createChart(dataset)
        displayChart(chart)
    }

    private fun displayChart(chart: JFreeChart) {
        val frame = ApplicationFrame("Pie Chart")
        frame.contentPane = ChartPanel(chart).apply {
            fillZoomRectangle = true
            isMouseWheelEnabled = true
            preferredSize = Dimension(750, 500)
        }
        frame.pack()
        frame.isVisible = true
    }

    private fun createDataset(data: Map<String, Double>): PieDataset {
        val dataset = DefaultPieDataset()
        data.forEach { dataset.setValue(it.key, it.value) }
        return dataset
    }

    private fun createChart(data: PieDataset): JFreeChart {
        val chart = ChartFactory.createPieChart(
            "Percentage Position Of Players",
            data
        )
        return chart
    }
}