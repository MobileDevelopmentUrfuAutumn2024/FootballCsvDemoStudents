package resolver

import model.ForwardFromTransferCost
import model.Team

interface Resolver {


    fun getCountWithoutAgency(): Int

    fun getBestScorerDefender(): Pair<String, Int>

    fun getTheExpensiveGermanPlayerPosition(): String

    fun getTheRudestTeam(): Team

    fun getForwardFromTransferCost(): List<ForwardFromTransferCost>
}