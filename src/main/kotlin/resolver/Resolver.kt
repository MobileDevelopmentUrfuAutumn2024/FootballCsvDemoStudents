package resolver

import model.Player
import model.Position
import model.Team

class Resolver(private val players: List<Player>) : IResolver {

    // Количество игроков без агентства
    override fun getCountWithoutAgency(): Int =
        players.count { it.agency.isEmpty() }

    // Лучший защитник по количеству голов
    override fun getBestScorerDefender(): Pair<String, Int> =
        players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goalsCount }
            ?.let { it.name to it.goalsCount }
            ?: ("No defender" to 0)

    // Позиция самого дорогого немецкого игрока
    override fun getTheExpensiveGermanPlayerPosition(): String =
        players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.let {
                when (it.position) {
                    Position.DEFENDER -> "Защитник"
                    Position.GOALKEEPER -> "Вратарь"
                    Position.MIDFIELD -> "Полузащитник"
                    Position.FORWARD -> "Нападающий"
                    else -> "Неизвестная позиция"
                }
            } ?: "Нет немецких игроков"

    // Команда с наибольшим количеством удалений на игрока
    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team }
            .maxBy { it.value.sumOf { player -> player.redCardsCount } / it.value.size }.key
    }
}
