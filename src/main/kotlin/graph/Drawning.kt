package graph

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.data.general.DefaultPieDataset
import java.awt.Dimension

class Drawning(val data: List<Pair<String, Int>>) {

    val datasetPie : DefaultPieDataset<String> = DefaultPieDataset<String>()
    val datasetCategory: DefaultCategoryDataset = DefaultCategoryDataset()

    init {
        data.forEach { datasetPie.setValue(it.first, it.second) }
        data.forEach { datasetCategory.setValue( it.second, "1", it.first) }
    }

    fun DrawPie(){
        val chart = ChartFactory.createPieChart(
            "Топ 10 команд с наивысшей суммарной трансферной стоимостью",
            datasetPie
        )
        visualization(chart)
    }

    fun DrawCategories(){
        val chart = ChartFactory.createBarChart(
            "Топ 10 команд с наивысшей суммарной трансферной стоимостью",
            "Название команд",
            "Суммарная трансферная стоимость команды",
            datasetCategory,
            PlotOrientation.HORIZONTAL,
            false,
            false,
            false
        )
        visualization(chart)
    }

    fun visualization(chart: JFreeChart){
        val frame = ApplicationFrame("Визуализация")
        frame.contentPane = ChartPanel(chart).apply {
            preferredSize = Dimension(1080, 720)
        }
        frame.pack()
        frame.isVisible = true
    }
}