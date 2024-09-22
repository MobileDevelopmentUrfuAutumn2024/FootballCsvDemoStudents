import resolver.Resolver

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.chart.ChartUtils;
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.data.general.DefaultValueDataset
import org.jfree.data.statistics.HistogramDataset
import java.io.File
import kotlin.coroutines.CoroutineContext


fun main(args: Array<String>) {
    val l = Resolver()
    print(l.getCountWithoutAgency())
    println("${l.getBestScorerDefender().toList()[0]}: ${l.getBestScorerDefender().toList()[1]}")
    println(l.getTheExpensiveGermanPlayerPosition())
    val grudge = l.getTheRudestTeam()
    println(grudge.redCardsNum/grudge.playersCount)
    val data = DefaultPieDataset<String>()
    l.topTransferPrice().forEach{data.setValue(it.name, it.transferCost);println("${it.name} = ${it.transferCost}")}
    val pie = ChartFactory.createPieChart("Команды с наивысшей трансферной стоимстью", data)
    (pie.plot as PiePlot<*>).labelGenerator = StandardPieSectionLabelGenerator("{1}")
    ChartUtils.saveChartAsPNG(File("src/main/kotlin/Views/pieChart.png"), pie, 800, 600)
}




