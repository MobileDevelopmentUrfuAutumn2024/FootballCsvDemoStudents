package chart

import model.Team
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.ChartUtils
import org.jfree.chart.JFreeChart
import org.jfree.chart.labels.ItemLabelAnchor
import org.jfree.chart.labels.ItemLabelPosition
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.renderer.category.BarRenderer
import org.jfree.chart.renderer.category.StandardBarPainter
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.chart.ui.TextAnchor
import org.jfree.data.category.CategoryDataset
import org.jfree.data.category.DefaultCategoryDataset
import java.awt.Dimension
import java.io.File

object ChartManager {
    fun createDataSet(data: List<Team>): CategoryDataset {
        val dataSet = DefaultCategoryDataset()
        data.forEach { dataSet.addValue(it.getTotalTransferCost(), "Category 1", it.teamName) }
        return dataSet
    }

    fun createBarChart(dataSet: CategoryDataset): JFreeChart {
        val chart = ChartFactory.createBarChart(
            "Топ-10 самых дорогих команд",
            "Название команды",
            "Общая трансферная стоймость",
            dataSet,
            PlotOrientation.HORIZONTAL,
            false,
            false,
            false
        )

        BarRenderer.setDefaultBarPainter(StandardBarPainter())

        val renderer = BarRenderer().apply {
            defaultItemLabelGenerator = StandardCategoryItemLabelGenerator()
            defaultItemLabelsVisible = true
            defaultPositiveItemLabelPosition =
                ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER)
        }

        chart.categoryPlot.renderer = renderer

        return chart
    }

    fun displayChart(chart: JFreeChart) {
        val frame = ApplicationFrame("График топ-10 самых дорогих команд")
        frame.contentPane = ChartPanel(chart).apply {
            preferredSize = Dimension(1200, 600)
        }
        frame.pack()
        frame.isVisible = true
    }

    fun saveChart(file: File, chart: JFreeChart) {
        ChartUtils.saveChartAsPNG(file, chart, 1200, 600)
    }
}