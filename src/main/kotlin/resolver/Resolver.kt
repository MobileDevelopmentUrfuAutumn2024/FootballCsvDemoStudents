package resolver

import model.Player
import model.Team
import org.jfree.data.general.DefaultPieDataset

class Resolver(private val players: List<Player>) : IResolver {

    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency.isNullOrBlank() }
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestScorerDefender: Player = players
            .filter { it.position == Player.Position.DEFENDER }
            .maxByOrNull { it.goalsCount } ?: throw NotFoundException("Best scorer defender not found")

        return bestScorerDefender
            .let { Pair(it.name, it.goalsCount) }
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        val expensiveGermanPlayer: Player = players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost } ?: throw NotFoundException("Expensive german player not found")

        return expensiveGermanPlayer.position.russianName
    }

    override fun getTheRudestTeam(): Team {
        val teamAverageRedCardsCount = players
            .groupBy { it.team }.entries
            .map { it -> Pair(it.key, it.value.sumOf { it.redCardsCount } / it.value.size) }

        val rudestTeamPair = teamAverageRedCardsCount
            .maxByOrNull { it.second } ?: throw NotFoundException("Rudest team not found")

        return rudestTeamPair.first
    }

    override fun getProportionOfPlayersInEachPosition(): DefaultPieDataset<String> {
        val positionCount = players.groupingBy { it.position }.eachCount()
        val dataset = DefaultPieDataset<String>()

        positionCount.forEach { (label, value) ->
            val realVal = value / players.count().toDouble()
            dataset.setValue(label.russianName, realVal)
        }

        return dataset
    }

}


class NotFoundException(message: String?) : RuntimeException(message) {
}