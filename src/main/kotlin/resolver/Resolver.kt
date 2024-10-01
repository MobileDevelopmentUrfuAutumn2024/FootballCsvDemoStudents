package resolver

import model.FootballPos
import model.Player
import model.Team

class Resolver(val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isNullOrEmpty() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter { it.position == FootballPos.DEFENDER }
            .maxByOrNull { it.playerStat.goals }?.let { Pair(it.name, it.playerStat.goals) }
            ?: throw NoSuchElementException("Не найден хотя бы один защитник")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { it.nation == "Germany" }.maxByOrNull { it.transferCost }?.position?.russianName
            ?: throw NoSuchElementException("Не найден хотя бы один немецкий футболист")
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }.maxByOrNull { it.value.sumOf { it.playerStat.redCardsCount }/it.value.size }?.key
            ?: throw NoSuchElementException("Не найдена хотя бы одна команда")
    }
}