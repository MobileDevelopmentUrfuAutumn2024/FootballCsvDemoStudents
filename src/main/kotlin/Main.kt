import jfree.JFree
import model.GoalKeeperFromTransferCost
import model.Player
import model.Team
import parser.CsvParser
import resolver.IResolver
import resolver.Resolver

fun main(args: Array<String>) {
    /* я 3 Вариант -> Сажин Егор */


    val listPlayers: List<Player> = CsvParser.parse("fakePlayers.csv")
    val resolver: IResolver = Resolver(listPlayers)

    val countPlayersWithoutAgency: Int = resolver.getCountWithoutAgency()
    val authorAndCount: Pair<String, Int> = resolver.getBestScorerDefender()
    val nameGermanyPlayer: String = resolver.getTheExpensiveGermanPlayerPosition()
    val countDeleteForOnePlayer: Team = resolver.getTheRudestTeam()

    println("Количество игроков, интересы которых не представляет агенство: $countPlayersWithoutAgency")
    println("Автор наибольшего числа голов из числа защитников и их количество: $authorAndCount")
    println("Русское название позиции самого дорогого немецкого игрока: $nameGermanyPlayer")
    println("Команда с наибольшим числом удалений на одного игрока: $countDeleteForOnePlayer")

    val listData: List<GoalKeeperFromTransferCost> = resolver.getGoalKeeperFromTransferCost()
    JFree.draw(listData)
}