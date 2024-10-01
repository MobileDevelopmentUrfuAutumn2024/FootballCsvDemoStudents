package graph

import graph.model.TeamTransferTotalCost
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.category.CategoryDataset
import org.jfree.data.category.DefaultCategoryDataset

object GraphDrawer {

    fun draw(data: List<TeamTransferTotalCost>) {
        val dataset = createDataset(data)
        val chart = createBarChart(dataset)
        displayChart(chart)
    }

    private fun createDataset(data: List<TeamTransferTotalCost>): CategoryDataset {
        val dataset = DefaultCategoryDataset()
        data.forEach { dataset.addValue(it.totalTransferCost, it.team, "") }

        return dataset
    }

    private fun createBarChart(data: CategoryDataset): JFreeChart {
        val chart = ChartFactory.createBarChart(
            "Топ-${data.rowCount} команд с наивысшей суммарной трансферной стоимостью",
            "Команда",
            "Суммарная трансферная стоимость",
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