package resolver

import model.Team

interface IResolver {
    fun getCountWithoutAgency(): Int
    fun getBestScorerDefender(): List<Pair<String, Int>>
    fun getTheExpensiveGermanPlayerPosition(): String
    fun getTheRudestTeam(): Team
}
