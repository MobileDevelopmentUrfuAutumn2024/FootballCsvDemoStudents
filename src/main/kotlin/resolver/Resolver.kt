package resolver

import model.Player
import model.Team
import translator.ITranslator
import java.lang.RuntimeException
import java.math.BigDecimal

class Resolver(
    val players: List<Player>,
    val teams: List<Team>,
    val translator: ITranslator
    ) : IResolver {



    override fun getCountWithoutAgency(): Int {
        return players.count { player -> player.agency.isBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val player = players
            .filter { player -> player.position == "DEFENDER" }
            .maxBy { player -> player.goals };
        return Pair(player.name, player.goals)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return translator.translate(
            players
                .filter { player -> player.nationality == "Germany" }
                .maxBy { player -> player.transferCost }
                .position
        ) ?: throw RuntimeException("НЕ НАЙДЕН ПЕРЕВОД")
    }

    override fun getTheRudestTeam(): Team {
        return teams
            .map { team: Team -> Pair(team, team.players.sumOf { player -> player.redCards } / team.players.size) }
            .maxBy { pair -> pair.second }
            .first
    }

    override fun getTopTeamsByTransferCost(): List<Pair<Team, Double>> {
        return teams
            .map { team: Team -> Pair(team, team.players.sumOf { player -> player.transferCost }) }
            .sortedBy { pair -> pair.second  }
            .take(10)

    }
}