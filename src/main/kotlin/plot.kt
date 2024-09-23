package main

import model.Player
import model.Position
import org.jfree.chart.ChartFactory
import org.jfree.chart.JFreeChart
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import java.io.File
import javax.imageio.ImageIO

fun getGoalsAndTransferValuesForForwards(players: List<Player>): List<Pair<Int, Int>> {
    return players
        .filter { it.position == Position.FORWARD }
        .map { it.transferCost to it.goalsCount }
}

fun createLineChart(data: List<Pair<Int, Int>>): JFreeChart {
    val series = XYSeries("Зависимость голов от трансферной стоимости")
    data.forEach { (transferValue, goals) ->
        series.add(transferValue.toDouble(), goals.toDouble())
    }

    val dataset = XYSeriesCollection(series)
    return ChartFactory.createXYLineChart(
        "Зависимость голов от трансферной стоимости",
        "Трансферная стоимость",
        "Количество голов",
        dataset
    )
}

fun saveChartAsImage(chart: JFreeChart, filePath: String) {
    val image = chart.createBufferedImage(800, 600)
    val outputFile = File(filePath)
    ImageIO.write(image, "png", outputFile)
}

fun buildAndSaveChart(players: List<Player>) {
    val data = getGoalsAndTransferValuesForForwards(players)
    val chart = createLineChart(data)
    saveChartAsImage(chart, "src/main/resources/goal_vs_transfer_value.png")
}
