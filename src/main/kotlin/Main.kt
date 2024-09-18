import resolver.Resolver
import view.Drawer

fun main() {
    val path = "src/main/resources/fakePlayers.csv"
    val resolver = Resolver(path)
    val drawer = Drawer("Какую долю занимают игроки из разных стран")

    val countWithoutAgency = resolver.getCountWithoutAgency()
    val bestScorerDefender = resolver.getBestScorerDefender()
    val expensiveGermanPlayerPosition = resolver.getTheExpensiveGermanPlayerPosition()
    val rudestTeam = resolver.getTheRudestTeam()

    println("Количество игроков, интересы которых не представляет агенство: $countWithoutAgency\n" +
            "Автор наибольшего числа голов из числа защитников и их количество: " +
            "${bestScorerDefender.first} - ${bestScorerDefender.second}\n" +
            "Русское название позиции самого дорогого немецкого игрока: $expensiveGermanPlayerPosition\n" +
            "Команда с наибольшим средним числом красных карточек на одного игрока: ${rudestTeam.name}")

    drawer.draw(resolver.players)
}