import resolver.Resolver

fun main(args: Array<String>) {
    val res: Resolver = Resolver()

    println("Количество игроков, интересы которых не представляет агенство: ${res.getCountWithoutAgency()}")
    println("Автора наибольшего числа голов из числа защитников и их количество: ${res.getBestScorerDefender()}")
    println("Русское название позиции самого дорогого немецкого игрока: ${res.getTheExpensiveGermanPlayerPosition()}")
    println("Команду с наибольшим числом удалений на одного игрока: ${res.getTheRudestTeam()}")

    res.drawGraph()
}
