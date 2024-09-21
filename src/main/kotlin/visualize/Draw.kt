package visualize

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.general.DefaultPieDataset
import org.jfree.data.general.PieDataset

class Draw {

    fun draw(data: Map<String, Double>) {
        val dataset = createDataset(data)
        val chart = createPieChart(dataset)
        displayChart(chart)
    }

    private fun createDataset(data: Map<String, Double>): PieDataset {
        val dataset = DefaultPieDataset()
        data.forEach { dataset.setValue(it.key, it.value) }

        return dataset
    }

    private fun createPieChart(data: PieDataset): JFreeChart {
        val chart = ChartFactory.createPieChart(
            "Доля игроков каждой из позиций от всех игроков",
            data
        )

        return chart
    }

    private fun displayChart(chart: JFreeChart) {
        val frame = ApplicationFrame("Balaba S")
        frame.contentPane = ChartPanel(chart).apply {
            fillZoomRectangle = true
            isMouseWheelEnabled = true
        }
        frame.pack()
        frame.isVisible = true
    }

}