package resolver

import model.Team

class Resolver(val teams: List<Team>) : IResolver {
    override fun getCountWithoutAgency(): Int {
        return teams.flatMap { it.getPlayers() }.count { it.getTransferData().agency.isEmpty() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return teams.flatMap { it.getPlayers() }
            .filter { it.getPersonData().position == "DEFENDER" }
            .map { it.getPersonData().name to it.getStatisticsData().goals }
            .maxByOrNull { it.second } ?: ("None" to 0)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return teams.flatMap { it.getPlayers() }
            .filter { it.getPersonData().nationality == "Germany" }
            .maxByOrNull { it.getTransferData().cost.toInt() }?.getPersonData()?.position ?: "None"
    }

    override fun getTheRudestTeam(): Team {
        return teams.map {
            it to it.getPlayers().sumOf { player -> player.getStatisticsData().redCards }
        }.maxByOrNull { it.second }?.first ?: Team("None", "None", emptyList())
    }
}