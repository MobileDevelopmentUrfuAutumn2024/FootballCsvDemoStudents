package resolver

import model.Player
import model.Position
import model.Team

class Resolver(private val players: List<Player>) : IResolver {
    // Выведите количество игроков, интересы которых не представляет агенство.
    override fun getCountWithoutAgency(): Int {
        return players.count { it.agency == "" }
    }

    // Выведите автора наибольшего числа голов из числа защитников и их количество.
    override fun getBestScorerDefender(): Pair<String, Int> {
        val bestDefender = players
            .filter { it.position == Position.DEFENDER }
            .maxByOrNull { it.goals }

        if (bestDefender == null) {
            return Pair("Нет защитников", 0)
        }

        return Pair(bestDefender.name, bestDefender.goals)
    }

    // Выведите русское название позиции самого дорогого немецкого игрока.
    override fun getTheExpensiveGermanPlayerPosition(): String {
        return players
            .filter { it.nationality == "Germany" }
            .maxByOrNull { it.transferCost }
            ?.position?.title ?: Position.DEFAULT.title
    }

    // Выберите команду с наибольшим средним числом красных карточек на одного игрока.
    override fun getTheRudestTeam(): Team {
        return players
            .groupBy { it.team }
            .maxByOrNull { it.value.map { player -> player.redCards }.average() }
            ?.key ?: Team("", "")
    }

    // Выведите топ-10 команд с наивысшей суммарной трансферной стоимостью с демонстрацией этих стоимостей.
    fun getTopTenTransferCost(): List<Pair<Team, Int>> {
        return players
            .groupBy { it.team }
            .mapValues { it.value.sumOf { player -> player.transferCost } }
            .toList()
            .sortedBy { it.second }
            .take(10)
    }
}