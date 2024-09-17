package resolver

import model.GoalKeeperFromTransferCost
import model.Player
import model.Position
import model.Team

/**
 * Сервис по обработке логики, связанной с футболистами.
 * Считаем, что нам подходит первый попавшийся, а дальше мы не учитываем
 */
class Resolver(val listPlayers: List<Player>) : IResolver {

    private val GERMANY: String = "Germany"

    /**
     * Выведите количество игроков, интересы которых не представляет агенство
     */
    override fun getCountWithoutAgency(): Int {

        return listPlayers.filter { player: Player -> player.agency.isEmpty() }
            .count()
    }

    /**
     * Выведите автора наибольшего числа голов из числа защитников и их количество
     */
    override fun getBestScorerDefender(): Pair<String, Int> {

        val listDefenders: List<Player> = listPlayers.filter { player: Player -> player.position == Position.DEFENDER }
        val player: Player = listDefenders.maxByOrNull { player: Player -> player.goalsCount }
            ?: throw IllegalArgumentException("Not found player")

        return Pair(player.name, player.goalsCount)
    }

    /**
     * Выведите русское название позиции самого дорогого немецкого игрока
     */
    override fun getTheExpensiveGermanPlayerPosition(): String {
        return listPlayers.filter { player: Player -> player.nationality == GERMANY }
            .maxByOrNull { player: Player -> player.transferCost }?.position?.name
            ?: throw IllegalArgumentException("Not found player")
    }

    /**
     * Выберите команду с наибольшим числом удалений на одного игрока
     */
    override fun getTheRudestTeam(): Team {
        return listPlayers.maxByOrNull { player: Player -> player.redCardsCount }?.team
            ?: throw IllegalArgumentException("Not found player")
    }

    /**
     * Выберите список футболистов для демонстрации
     * зависимости количества забитых голов от трансферной стоимости для нападающих
     */
    override fun getGoalKeeperFromTransferCost(): List<GoalKeeperFromTransferCost> {
        return listPlayers.filter { player: Player -> player.position == Position.FORWARD }
            .map {
                GoalKeeperFromTransferCost(
                    transferCost = it.transferCost,
                    goalsCount = it.goalsCount
                )
            }
    }
}