package graph

import graph.model.TeamTransferTotalCost
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.general.DefaultPieDataset
import org.jfree.data.general.PieDataset

object GraphDrawer {

    fun draw(data: List<TeamTransferTotalCost>) {
        val dataset = createDataset(data)
        val chart = createPieChart(dataset)
        displayChart(chart)
    }

    private fun createDataset(data: List<TeamTransferTotalCost>): PieDataset {
        val dataset = DefaultPieDataset()
        data.forEach { dataset.setValue(it.team, it.totalTransferCost) }

        return dataset
    }

    private fun createPieChart(data: PieDataset): JFreeChart {
        val chart = ChartFactory.createPieChart(
            "Топ-${data.keys.size} команд с наивысшей суммарной трансферной стоимостью",
            data
        )

        return chart
    }

    private fun displayChart(chart: JFreeChart) {
        val frame = ApplicationFrame("Kropotin I.D.")
        frame.contentPane = ChartPanel(chart).apply {
            fillZoomRectangle = true
            isMouseWheelEnabled = true
        }
        frame.pack()
        frame.isVisible = true
    }

}