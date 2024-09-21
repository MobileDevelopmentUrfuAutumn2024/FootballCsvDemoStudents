package resolver

import model.*
import parser.CsvParser
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.ui.ApplicationFrame
import org.jfree.data.general.DefaultPieDataset

class Resolver : IResolver{

    private val players = CsvParser.getPlayers()

    override fun getCountWithoutAgency(): Int {
        var counter: Int = 0
        players.forEach { player ->  
            if (player.agency == "") {
                counter++
            }
        }
        return counter
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val player: Player? = players.filter {
            it.position == "DEFENDER"
        }.maxByOrNull { it.goals }

        var bestScorerDef: Pair<String, Int>? = player?.let { Pair(it.name, it.goals) }

        return bestScorerDef ?: throw NoSuchElementException("No DEFENDER? :(")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val player: Player? = players.filter {
            it.nationality == "Germany"
        }.maxByOrNull { it.transferCost }

        when (player?.position) {
            "DEFENDER" -> return "ЗАЩИТНИК"
            "FORWARD" -> return "НАПАДАЮЩИЙ"
            "MIDFIELD" -> return "ПОЛУЗАЩИТНИК"
            "GOALKEEPER" -> return "ВРАТАРЬ"
            else -> throw NoSuchElementException("No German? :(")
        }
    }

    override fun getTheRudestTeam(): Team {
        val arr: Map<Team, List<Player>> = players.groupBy { it.team }

        val theRudestTeam: Team = arr.maxByOrNull {
            it.value.sumOf { it.redCards } / it.value.size
        }?.key
            ?: throw NoSuchElementException("No Teams? :(")

        return theRudestTeam
    }

    private fun getPartsOfEveryPosition(): MutableMap<String, Double> {
        var parts: MutableMap<String, Double> = mutableMapOf(
            "DEFENDER" to 0.0,
            "FORWARD" to 0.0,
            "MIDFIELD" to 0.0,
            "GOALKEEPER" to 0.0
        )

        players.forEach { player ->
            parts[player.position] = parts[player.position]!! + 1
        }

        val allPlayersSum: Double = parts.values.sum()

        parts["DEFENDER"] = parts["DEFENDER"]?.div((allPlayersSum))
            ?: throw ArithmeticException("No good Integers? :(")
        parts["FORWARD"] = parts["FORWARD"] ?.div(allPlayersSum)
            ?: throw ArithmeticException("No good Integers? :(")
        parts["MIDFIELD"] = parts["MIDFIELD"]?.div(allPlayersSum)
            ?: throw ArithmeticException("No good Integers? :(")
        parts["GOALKEEPER"] = parts["GOALKEEPER"]?.div(allPlayersSum)
            ?: throw ArithmeticException("No good Integers? :(")

        return parts
    }

    fun drawGraph() {
        val parts: Map<String, Double> = getPartsOfEveryPosition()

        val dataset: DefaultPieDataset<String> = DefaultPieDataset<String>()
        dataset.setValue("DEFENDER", parts["DEFENDER"])
        dataset.setValue("FORWARD", parts["FORWARD"])
        dataset.setValue("MIDFIELD", parts["MIDFIELD"])
        dataset.setValue("GOALKEEPER", parts["GOALKEEPER"])

        val chart: JFreeChart = ChartFactory.createPieChart(
            "Доля игроков каждой из позиций от общего числа игроков",
            dataset,
        )

        val window: ApplicationFrame = ApplicationFrame("Визуализация (Филиппов Петр Алексеевич Вариант 1)")
        window.contentPane = ChartPanel(chart)

        window.pack()
        window.isVisible = true
    }
}