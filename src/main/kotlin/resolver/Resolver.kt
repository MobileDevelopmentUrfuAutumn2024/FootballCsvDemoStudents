package resolver

import model.GoalKeeperFromTransferCost
import model.Player
import model.Position
import model.Team

/**
 * Сервис по обработке логики, связанной с футболистами.
 * Считаем, что нам подходит первый попавшийся, а дальше мы не учитываем
 */
object Resolver : IResolver {

    private val GERMANY: String = "GERMANY"

    /**
     * Выведите количество игроков, интересы которых не представляет агенство
     */
    override fun getCountWithoutAgency(listPlayers: List<Player>): Int {

        return listPlayers.filter { player: Player -> player.agency == null }
            .count()
    }

    /**
     * Выведите автора наибольшего числа голов из числа защитников и их количество
     */
    override fun getBestScorerDefender(listPlayers: List<Player>): Pair<String, Int> {

        val listDefenders: List<Player> = listPlayers.filter { player: Player -> player.position.equals(Position.DEFENDER) }
        val player: Player = listDefenders.maxBy { player: Player -> player.goalsCount }

        return Pair(player.name, player.goalsCount)
    }

    /**
     * Выведите русское название позиции самого дорогого немецкого игрока
     */
    override fun getTheExpensiveGermanPlayerPosition(listPlayers: List<Player>): String {
        return listPlayers.filter { player: Player -> player.nationality.equals(GERMANY) }
            .maxBy { player: Player -> player.transferCost }.position.name
    }

    /**
     * Выберите команду с наибольшим числом удалений на одного игрока
     */
    override fun getTheRudestTeam(listPlayers: List<Player>): Team {
        return listPlayers.maxBy { player: Player -> player.redCardsCount }.team
    }

    /**
     * Выберите список футболистов для демонстрации
     * зависимости количества забитых голов от трансферной стоимости для нападающих
     */
    override fun getGoalKeeperFromTransferCost(listPlayers: List<Player>): List<GoalKeeperFromTransferCost> {
        return listPlayers.filter { player: Player -> player.position.equals(Position.GOALKEEPER) }
            .map {
                GoalKeeperFromTransferCost(
                    transferCost = it.transferCost,
                    goalsCount = it.goalsCount
                )
            }
    }
}