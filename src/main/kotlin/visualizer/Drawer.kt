package visualizer

import model.Player
import model.Position
import org.jfree.chart.ChartFactory
import org.jfree.chart.JFreeChart
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection

class Drawer {

    fun createDataset(players: List<Player>): XYSeriesCollection {
        val series = XYSeries("Goals vs Transfer Cost")

        for (player in players.filter { it.position == Position.FORWARD }) {
            series.add(player.transferCost, player.goalsCount)
        }

        val dataset = XYSeriesCollection()
        dataset.addSeries(series)
        return dataset
    }

    fun createChart(dataset: XYSeriesCollection): JFreeChart {
        return ChartFactory.createXYLineChart(
            "Goals vs Transfer Cost",
            "Transfer Cost",
            "Goals Scored",
            dataset
        )
    }
}