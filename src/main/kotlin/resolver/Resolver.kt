package resolver

import model.EPosition
import model.Player
import model.Team

class Resolver(private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isNullOrEmpty() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.position == EPosition.DEFENDER }
            .maxByOrNull { it.goals ?: 0 }?.let { Pair(it.name, it.goals ?: 0) }
            ?: throw NoSuchElementException("Нет защитников")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost ?: 0 }?.position?.russianName
            ?: throw NoSuchElementException("Нет немецких футболистов")
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }
            .maxByOrNull { it.value.sumOf { it.redCards ?: 0 } / it.value.size }?.key
            ?: throw NoSuchElementException("Не найдена хотя бы одна команда")
    }
}