import jfree.JFree
import model.GoalKeeperFromTransferCost
import model.Player
import parser.CsvParser
import resolver.Resolver

fun main(args: Array<String>) {
    /* я 3 Вариант -> Сажин Егор */
    println("Yeah rock!")

    val listPlayers: List<Player> = CsvParser.parse("fakePlayers.csv")

    val listData: List<GoalKeeperFromTransferCost> = Resolver.getGoalKeeperFromTransferCost(listPlayers)
    JFree.draw(listData)
}