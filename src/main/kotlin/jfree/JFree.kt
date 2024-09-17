package jfree

import model.GoalKeeperFromTransferCost
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import javax.swing.JFrame
import javax.swing.SwingUtilities

object JFree {

    fun draw(listData: List<GoalKeeperFromTransferCost>) {
        SwingUtilities.invokeLater {
            val frame = JFrame("Сажин_Е_А")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.add(createChartPanel(listData))
            frame.pack()
            frame.setLocationRelativeTo(null)
            frame.isVisible = true
        }
    }

    private fun createChartPanel(listData: List<GoalKeeperFromTransferCost>): ChartPanel {
        val dataset = createDataset(listData)
        val chart = createChart(dataset)
        return ChartPanel(chart)
    }

    private fun createDataset(listData: List<GoalKeeperFromTransferCost>): XYDataset {

        val series = XYSeries("Goals vs Transfer Value")

        listData.forEach { series.add(it.transferCost, it.goalsCount) }

        val dataset = XYSeriesCollection()
        dataset.addSeries(series)
        return dataset
    }

    private fun createChart(dataset: XYDataset): JFreeChart {
        val chart = ChartFactory.createXYLineChart(
            "Зависимость количества забитых голов от трансферной стоимости для нападающих",
            "Transfer Value",
            "Goals Scored",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        )

        val plot = chart.xyPlot as XYPlot
        val renderer = XYLineAndShapeRenderer()
        plot.renderer = renderer
        plot.rangeAxis = (NumberAxis("Количество забитых голов"))
        plot.domainAxis = NumberAxis("Трансферная стоимость для нападающих")

        return chart
    }
}

data class PlayerData(val transferValue: Double, val goalsScored: Double)