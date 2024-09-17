package resolver

import model.ForwardFromTransferCost
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

        return listPlayers.count { player: Player -> player.agency.isEmpty() }
    }

    /**
     * Выведите автора наибольшего числа голов из числа защитников и их количество
     */
    override fun getBestScorerDefender(): Pair<String, Int> {

        val player: Player =
            listPlayers.filter { player: Player -> player.position == Position.DEFENDER }
                .maxByOrNull { player: Player -> player.goalsCount }
                ?: throw IllegalArgumentException("Not found player")

        return Pair(player.name, player.goalsCount)
    }

    /**
     * Выведите русское название позиции самого дорогого немецкого игрока
     */
    override fun getTheExpensiveGermanPlayerPosition(): String {

        val maxCountPlayer: String = listPlayers.filter { player: Player -> player.nationality == GERMANY }
            .maxByOrNull { player: Player -> player.transferCost }?.position?.name
            ?: throw IllegalArgumentException("Not found player")


        return translate(maxCountPlayer)
    }

    /**
     * Выберите команду с наибольшим числом удалений на одного игрока.
     * Надо вывести команду с наибольшим средним числом красных карточек на одного игрока. То есть кол-во карточек на команду / кол-во игроков
     */
    override fun getTheRudestTeam(): Team {

        return listPlayers.groupBy { it.team }
            .mapValues { (_, players) ->
                players.map { it.redCardsCount }.average()
            }
            .maxByOrNull { it.value }?.key
            ?: throw IllegalArgumentException("Not found player")
    }

    /**
     * Выберите список футболистов для демонстрации
     * зависимости количества забитых голов от трансферной стоимости для нападающих
     */
    override fun getForwardFromTransferCost(): List<ForwardFromTransferCost> {
        return listPlayers.filter { it.position == Position.FORWARD }
            .map {
                ForwardFromTransferCost(
                    transferCost = it.transferCost,
                    goalsCount = it.goalsCount
                )
            }
    }

    private fun translate(maxCountPlayer: String): String {

        return when (maxCountPlayer) {
            Position.MIDFIELD.name -> "Полузащитник"
            Position.DEFENDER.name -> "Защитник"
            Position.FORWARD.name -> "Нападающий"
            Position.GOALKEEPER.name -> "Вратарь"
            else -> "Not Found"
        }
    }
}