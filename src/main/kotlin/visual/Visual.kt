package visual

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.general.Dataset
import org.jfree.data.general.DefaultPieDataset
import org.jfree.data.general.PieDataset
import java.awt.Dimension


class Visual(title: String) : ApplicationFrame(title) {

    fun view(players: List<Player>) {
        val dataForDiagram = insertingData(players)
        val dataset = createDataset(dataForDiagram)
        val chart = createChart(dataset)
        val chartPanel = ChartPanel(chart)

        chartPanel.preferredSize = Dimension(800, 600)
        contentPane = chartPanel

        this.pack()
        this.isVisible = true
    }


    private fun createDataset(data: Map<String, Double>): Dataset {
        val dataset = DefaultPieDataset()
        data.forEach { (key, value) -> dataset.setValue(key, value) }
        return dataset
    }


    private fun createChart(dataset: Dataset): JFreeChart {
        val chart = ChartFactory.createPieChart(title, dataset as PieDataset)
        return chart
    }

    private fun insertingData(players: List<Player>): Map<String, Double> {
        val dataForDiagram = HashMap<String, Double>()

        for (i in players.groupBy { it.nationality }) {
            dataForDiagram[i.key] = i.value
                .count()
                .calculatePercentBy(players.count())
        }

        return dataForDiagram
    }
}


private fun Int.calculatePercentBy(max: Int): Double {
    return (this / max.toDouble()) * 100
}