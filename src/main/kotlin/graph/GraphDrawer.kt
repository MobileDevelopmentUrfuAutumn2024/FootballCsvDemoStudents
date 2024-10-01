package graph

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.general.DefaultPieDataset

object GraphDrawer {

    fun draw(data: DefaultPieDataset<String>) {
        val chart = ChartFactory.createPieChart("Шарипов Рустам, 1 вариант: Какую долю занимают игроки каждой из позиций?", data)
        displayChart(chart)
    }

    private fun displayChart(chart: JFreeChart) {
        val frame = ApplicationFrame("Шарипов Рустам")
        frame.contentPane = ChartPanel(chart).apply {
            fillZoomRectangle = true
            isMouseWheelEnabled = true
        }
        frame.pack()
        frame.isVisible = true
    }

}