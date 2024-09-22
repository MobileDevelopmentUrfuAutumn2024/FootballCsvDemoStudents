package resolver

import model.Player
import model.Position
import model.Team

class Resolver(private val players: List<Player>) : IResolver {


    override fun getCountWithoutAgency(): Int =
        players.count { it.agency.isEmpty() }

    override fun getBestScorerDefender(): Pair<String, Int> =
        players
            .filter { it.position == Position.DEFENDER }
            .maxBy { it.goalsCount }
            .let { it.name to it.goalsCount }

    override fun getTheExpensiveGermanPlayerPosition(): String =
        players
            .filter{ it.nationality == "Germany" }
            .maxBy { it.transferCost }
            .let {
                when(it.position) {
                    Position.DEFENDER -> "Защитник"
                    Position.FORWARD -> "Нападающий"
                    Position.MIDFIELD -> "Полузащитник"
                    Position.GOALKEEPER -> "Вратарь"
                    else -> "Другие позиции"
                }
            }

    override fun getTheRudestTeam(): Team =
        players
            .groupBy { it.team }
            .mapValues { (_, players) ->
                players.map { it.redCardsCount }.average()
            }
            .maxBy { it.value }.key

}