package resolver

import model.Player
import model.Position
import model.Team
import parser.CsvParser

class ResolverImpl(private val players: List<Player>) : Resolver {

    override fun getCountWithoutAgency(): Int {
        var counter = 0
        players.forEach { player ->
            if (player.agency == null) {
                counter++
            }
        }
        return counter
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestDefender: Player? = players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goals }

        return if (bestDefender != null) {
            Pair(bestDefender.name, bestDefender.goals)
        } else {
            throw NoSuchElementException("Защитники не найдены")
        }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensiveGermanPlayer: Player? = players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }

        return expensiveGermanPlayer?.position?.title
            ?: throw NoSuchElementException("Игроки из Германии не найдены")
    }

    override fun getTheRudestTeam(): Team {
        val playersByTeam: Map<Team, List<Player>> = players.groupBy { it.team }

        val rudestTeam: Team = playersByTeam.maxByOrNull {
            it.value.sumOf { player -> player.redCards } / it.value.size
        }?.key ?: throw NoSuchElementException("Команды не найдены")

        return rudestTeam
    }
}