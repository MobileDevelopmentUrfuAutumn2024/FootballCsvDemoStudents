package graph

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.general.DefaultPieDataset

class GraphImpl(
    private val players: List<Player>,
) : Graph {

    fun drawGraph() {
        val dataset = createDataset()
        val chart: JFreeChart = ChartFactory.createPieChart(
            "Доля игроков по странам",
            dataset,
        )

        val window = ApplicationFrame("Визуализация (Коломиец Данила Вариант 1)")
        window.contentPane = ChartPanel(chart)

        window.pack()
        window.isVisible = true
    }

    private fun createDataset() : DefaultPieDataset<String> {
        val dataset = DefaultPieDataset<String>()
        val shareOfPlayersByCountry = getShareOfPlayersByCountry(players)

        shareOfPlayersByCountry.forEach { (country, share) ->
            dataset.setValue(country, share)
        }

        return dataset
    }

    private fun getShareOfPlayersByCountry(players: List<Player>) : Map<String, Double>  =
        players.groupBy { it.nationality }.mapValues { it.value.size.toDouble() / players.size }
}