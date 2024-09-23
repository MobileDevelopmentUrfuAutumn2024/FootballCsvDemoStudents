import model.Player
import parser.CsvParser
import resolver.Resolver
import visualizer.Visualizer

fun main(args: Array<String>) {
    val players: List<Player> = CsvParser.readPlayersFromFile("src/main/resources/fakePlayers.csv");
    val resolver = Resolver()

    println("Количество игроков, интересы которых не представляет агенство: ${resolver.countPlayersWithoutAgency(players)}")

    resolver.topScorerAmongDefenders(players)?.let {
        println("Автор наибольшего числа голов из числа защитников и их количество: ${it.first} с ${it.second} голами")
    } ?: println("Защитников нет.")

    println("Русское название позиции самого дорогого немецкого игрока: ${resolver.mostExpensiveGermanPlayerPosition(players)}")

    resolver.teamWithMostRedCardsPerPlayer(players)?.let {
        println("Команда с наибольшим средним числом красных карточек на игрока: ${it.name} из ${it.city}")
    } ?: println("Нет команд.")


    Visualizer().draw("Player different countries", resolver.playerShareByNationality(players)
    )
}