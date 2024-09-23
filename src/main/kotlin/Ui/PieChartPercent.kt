package ui

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.labels.StandardPieSectionLabelGenerator
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.PieDataset
import org.jfree.ui.ApplicationFrame
import javax.swing.JPanel


class PieChartPercent(private val title: String?, private val pieDataset: PieDataset) : ApplicationFrame(title) {
    init {
        this.contentPane = createDemoPanel()
    }

    private fun createChart(dataset: PieDataset): JFreeChart {
        val chart = ChartFactory.createPieChart(title, dataset, true, false, false)
        (chart.plot as PiePlot).labelGenerator = StandardPieSectionLabelGenerator("{1}%")
        return chart
    }

    private fun createDemoPanel(): JPanel {
        val chart = createChart(pieDataset)
        return ChartPanel(chart)
    }
}