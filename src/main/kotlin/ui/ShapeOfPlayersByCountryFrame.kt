package ui

import model.Player
import org.jfree.data.general.DefaultPieDataset
import org.jfree.ui.RefineryUtilities

object ShareOfPlayersByCountryFrame {
    fun show(players: List<Player>){
        val pieDataset = DefaultPieDataset()
        players.groupBy { it.nationality }
            .map { it.key to it.value.count().toDouble() / players.count() * 100 }
            .forEach { pieDataset.setValue(it.first, it.second) }
        PieChartPercent("Доля игроков по странам", pieDataset).apply {
            pack()
            RefineryUtilities.centerFrameOnScreen(this)
            isVisible = true
        }
    }
}