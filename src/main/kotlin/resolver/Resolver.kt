package resolver

import model.Position
import model.Team
import parser.CsvParser

class Resolver : IResolver{
    private val dataList = CsvParser.parser()
    private var teamList: MutableList<Team> = mutableListOf()

    override fun getCountWithoutAgency(): Int {
        return dataList.filter{it.agency==""}.size
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestDefender = dataList.filter{it.position==Position.DEFENDER}.sortedBy { it.goals }[0]
        return Pair(bestDefender.name, bestDefender.goals)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensiveGermanPlayer = dataList
            .filter { it.nationality == "Germany" }
            .sortedBy { it.transferCost }.last()
        return expensiveGermanPlayer.position.PositionName
    }

    override fun getTheRudestTeam(): Team {
        for(player in dataList){
            player.team.redCardsNum += player.redCards
            player.team.playersCount += 1
        }
        return dataList.sortedBy{ it.team.redCardsNum }.last().team
    }

    fun topTransferPrice(): List<Team>{
        val teamList: MutableMap<String, Team> = mutableMapOf()
        for(player in dataList){
            val name = player.team.name
            val cost = teamList[name] ?:Team(name, player.team.city)
            teamList[name] = Team(name = name, city = cost.city, transferCost = player.transferCost.toInt() + cost.transferCost)
        }
        return teamList.values.sortedBy { it.transferCost }.asReversed().take(10)
    }
}