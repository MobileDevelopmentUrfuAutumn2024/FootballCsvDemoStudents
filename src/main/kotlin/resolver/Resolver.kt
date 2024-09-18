package resolver

import model.Player
import model.Position
import model.Team
import model.getPosition
import parser.CsvParser

/**
 * Решение задач
 * @param path путь к файлу
 */
class Resolver(
    path: String
) : IResolver {
    private val csvParser = CsvParser
    val players: List<Player> = csvParser.parse(path)

    override fun getCountWithoutAgency(): Int = players
        .filter { it.agency.isNullOrEmpty() }.size

    override fun getBestScorerDefender(): Pair<String, Int> {
        val player = players
            .filter { getPosition(it.position.name) == Position.DEFENDER }
            .maxBy { it.goals }

        return Pair(player.name, player.goals)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String = getPosition(
        players.maxBy { it.transferCost }.position.name
    ).pos

    override fun getTheRudestTeam(): Team {
        val teams: MutableList<Team> = mutableListOf()

        players
            .groupBy { it.team }
            .forEach { teams.add(Team(it.key, it.value)) }

        return teams.maxBy { it.players.sumOf { players -> players.redCards } / it.players.count() }
    }
}