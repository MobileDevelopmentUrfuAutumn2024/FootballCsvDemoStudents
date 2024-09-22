package resolver

import model.Player
import model.Team

fun Resolver(players: List<Player>): Resolver = ResolverImpl(players)

private class ResolverImpl(
    private val players: List<Player>,
) : Resolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        return players
            .filter { it.position == Player.Position.DEFENDER }
            .maxByOrNull { it.goals }
            ?.let { it.name to it.goals }
            ?: throw NoSuchElementException("There were no defenders among the players.")
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality.equals("germany", true) }
            .maxByOrNull { it.transferCost }
            ?.position
            ?.displayName
            ?: throw NoSuchElementException("There were no Germans among the players.")
    }

    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team }
            .maxByOrNull { (_, v) -> v.map { it.redCards }.average() }
            ?.key
            ?: throw NoSuchElementException("The players list was empty.")
    }

    private val Player.Position.displayName: String
        get() = when (this) {
            Player.Position.FORWARD -> "нападающий"
            Player.Position.MIDFIELD -> "полузащитник"
            Player.Position.DEFENDER -> "защитник"
            Player.Position.GOALKEEPER -> "вратарь"
        }
}
