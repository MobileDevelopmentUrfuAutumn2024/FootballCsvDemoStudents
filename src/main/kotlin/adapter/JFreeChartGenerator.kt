package adapter

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.data.general.DefaultPieDataset
import org.jfree.data.general.PieDataset
import java.awt.Dimension
import javax.swing.JFrame

object JFreeChartGenerator {

    fun generatePlayersDistributionByCountryChart(players: List<Player>) {
        val pieChart = ChartFactory.createPieChart(
            "Распределение игроков по странам",
            createPieDataset(players),
            true,
            true,
            true
        )

        val frame = JFrame("Распределение игроков по странам")
        frame.size = Dimension(800, 600)
        frame.contentPane = ChartPanel(pieChart).apply {
            fillZoomRectangle = true
            isMouseWheelEnabled = true
            isDomainZoomable = true
            isRangeZoomable = true
            isOpaque = true
        }
        frame.setLocationRelativeTo(null)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.pack()
        frame.isVisible = true
    }

    private fun createPieDataset(data: List<Player>): PieDataset {
        val dataset = DefaultPieDataset()

        data.groupingBy { it.nationality }.eachCount().map {
            (nationality, count) -> dataset.setValue(nationality, count)
        }

        return dataset
    }

}