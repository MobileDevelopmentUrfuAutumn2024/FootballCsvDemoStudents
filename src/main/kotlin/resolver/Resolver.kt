package resolver

import model.Player
import model.Team
import org.w3c.dom.css.Counter

class Resolver(private val players: List<Player>): IResolver {
    override fun getCountWithoutAgency(): Int {
        var countPlayer: Int = 0

        for (player in players) {
            if (player.agent == "") {
                countPlayer++
            }
        }

        return countPlayer

    }

    override fun getBestScorerDefender(): Pair<String, Int> {

        var maxCountGoal: Int = 0
        var nameFootballer: String = ""

        for (player in players) {
            if (player.position == Player.Position.valueOf("DEFENDER")) {
                if (player.countGoal > maxCountGoal) {
                    maxCountGoal = player.countGoal
                    nameFootballer = player.name
                }
            }
        }

        val result: Pair<String, Int> = nameFootballer to maxCountGoal

        return result

    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        var priceFootballer: Double = 0.0
        var positionFootballer: String = ""

        for (player in players) {
            if (player.nationality == "Germany" && priceFootballer < player.price) {
                priceFootballer = player.price
                positionFootballer = player.position.russianName
            }
        }

        return positionFootballer

    }

    override fun getTheRudestTeam(): Team {
        val teamPlayer = mutableMapOf<Team, Int>()
        val teamRedCard = mutableMapOf<Team, Int>()

        for (player in players) {
            val nameTeam = player.team
            if (!teamPlayer.containsKey(nameTeam)) {
                teamPlayer[nameTeam] = 1
                teamRedCard[nameTeam] = player.countRedCard
            } else {
                if (teamPlayer.containsKey(nameTeam))
                teamPlayer[nameTeam] = teamPlayer[nameTeam]!! + 1
                teamRedCard[nameTeam] = teamRedCard[nameTeam]!! + player.countRedCard
            }
        }

        var meanRedCard: Double = 0.0
        val teams: MutableSet<Team> = teamPlayer.keys
        var nameTeam: Team = teams.first()

        for (team in teams) {
            val redCard: Double = (teamRedCard[team]?.toDouble() ?: 0.0) / teamPlayer[team]!!
            if (redCard > meanRedCard) {
                meanRedCard = redCard
                nameTeam = team
            }
        }

        return nameTeam
    }

}