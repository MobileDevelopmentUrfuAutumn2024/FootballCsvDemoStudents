package resolver

import model.Position
import model.Team
import parser.CsvParser

class Resolver : IResolver{
    private val dataList = CsvParser.parser()

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
        }
        return dataList.sortedBy { it.team.redCardsNum }.first().team
    }
}