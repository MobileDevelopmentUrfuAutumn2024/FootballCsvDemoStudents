package resolver

import model.*
import parser.CsvParser

class Resolver: IResolver{
    val players = CsvParser.Parse()
    override fun getCountWithoutAgency(): Int {
        return players.count{
            i -> i.agency == ""
        }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val i: Player? = players.filter {
            it.position == PositionsRu.DEFENDER
        }.maxByOrNull { it.goals }
        return i?.let { Pair(it.name, it.goals) }
            ?: throw NoSuchElementException("Data error")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val i: Player? = players.filter {
            it.nationality == "Germany"
        }.maxByOrNull { it.transferCost }
        return i?.position?.posRu
            ?: throw NoSuchElementException("Data error")
    }

    override fun getTheRudestTeam(): Team {
        val teamDict: Map<Team, List<Player>> = players.groupBy { it.team }
        return teamDict.maxByOrNull {
            it.value.sumOf { it.redCards } / it.value.size
        }?.key ?: throw NoSuchElementException("Data error")
    }
}