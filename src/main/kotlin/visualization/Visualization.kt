package visualization

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFrame

class Visualization {
    fun printAndSavePlot(players : List<Player>): Unit {
        val sortedPlayers = players.sortedBy { it.position }

        val dataset = DefaultCategoryDataset()

        for (player in sortedPlayers) {
            dataset.addValue(player.transferCost, "TransferCoast", player.position)
        }

        val chart = ChartFactory.createBarChart(
            "Transfer coast depends on the position",
            "Position",
            "Transfer coast",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            true
        )

        val panel = ChartPanel(chart)

        val frame = JFrame("Зависимость цены от позиции")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane.add(panel)
        frame.setSize(1000, 700)
        frame.isVisible = true

        val image = chart.createBufferedImage(1000, 700)
        val outputFile = File("src/main/resources/TransferCoastDependsOnThePosition.png")
        ImageIO.write(image, "png", outputFile)
    }
}