import parser.readPlayersFromFile
import resolver.Resolver
import visual.Visual

fun main() {
    val players = readPlayersFromFile("src/main/resources/fakePlayers.csv")
    val resolver = Resolver()
    val visual = Visual("Какую долю игроки из разных стран занимают?")

    println("Количество игроков без агенства: ${resolver.countPlayersWithoutAgency("Агентство X", players)}")
    val topDefender = resolver.topScoringDefender(players)
    println("Лучший защитник по голам: ${topDefender?.first} с ${topDefender?.second} голами")
    println("Позиция самого дорогого немецкого игрока: ${resolver.positionOfMostExpensiveGermanPlayer(players)}")

    val teams = players.map { it.team }.distinct()
    val teamWithHighRedCards = resolver.teamWithHighestAverageRedCards(teams, players)
    println("Команда с наибольшим средним количеством красных карточек: ${teamWithHighRedCards?.team}")
    visual.view(players)
}
