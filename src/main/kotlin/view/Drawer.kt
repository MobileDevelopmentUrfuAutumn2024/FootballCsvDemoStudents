package view

import model.Player
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.general.Dataset
import org.jfree.data.general.DefaultPieDataset
import org.jfree.data.general.PieDataset
import java.awt.Dimension


/**
 * Вариант 4. Покажите, какую долю занимают игроки из разных стран.
 */
class Drawer(title: String) : ApplicationFrame(title) {
    /**
     * Рисовать диаграмму
     * @param players список игроков
     */
    fun draw(players: List<Player>) {
        val dataForDiagram = insertingData(players)

        val dataset = createDataset(dataForDiagram)
        val chart = createChart(dataset)
        val chartPanel = ChartPanel(chart)

        chartPanel.preferredSize = Dimension(600, 400)
        contentPane = chartPanel

        this.pack()
        this.isVisible = true;
    }

    /**
     * Вставка данных
     * @param players игроки
     * @return данные в виде коллекции ключ-значение
     */
    private fun insertingData(players: List<Player>): Map<String, Double> {
        val dataForDiagram = HashMap<String, Double>()

        for (i in players.groupBy { it.city }) {
            dataForDiagram[i.key] = i.value
                .count()
                .calculatePercentBy(players.count())
        }

        return dataForDiagram
    }

    /**
     * Создать датасет
     * @param data Хэшмап с информацией для датасета
     */
    private fun createDataset(data: Map<String, Double>): Dataset {
        val dataset = DefaultPieDataset()
        data.forEach { (key, value) -> dataset.setValue(key, value) }
        return dataset
    }

    /**
     * Создать чарт
     * @param dataset датасет информации
     * @return Чарт для диаграммы
     */
    private fun createChart(dataset: Dataset): JFreeChart {
        val chart = ChartFactory.createPieChart(title, dataset as PieDataset)
        return chart
    }
}

/**
 * Функция расширение для подсчёта процента от числа
 * @param max от какого числа процент
 * @return процент
 */
private fun Int.calculatePercentBy(max: Int): Double {
    return (this / max.toDouble()) * 100
}