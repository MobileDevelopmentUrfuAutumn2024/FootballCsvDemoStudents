package resolver

import model.Player
import model.Position
import model.Team


class Resolver(private val players: List<Player>): IResolver{
    override fun getCountWithoutAgency(): Int {
        return players.filter{it.agency.isNullOrEmpty()}.size
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players.filter{it.position== Position.DEFENDER}
            .maxBy { it.goalsCount}
            .let { it.name to it.goalsCount }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players.filter { it.nationality=="Germany" }.maxBy { it.transferCost }.let {
            when (it.position) {
                Position.DEFENDER -> "Защитник"
                Position.GOALKEEPER -> "Вратарь"
                Position.MIDFIELD -> "Полузащитник"
                Position.FORWARD -> "Нападающий"
                else -> "" }
        }
    }

    override fun getTheRudestTeam(): Team {
        return players.groupBy { it.team }.maxBy { it.value.sumOf{ player -> player.redCardsCount } / it.value.size}.key

    }

}