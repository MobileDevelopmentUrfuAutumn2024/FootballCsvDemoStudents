import parser.CsvParser
import resolver.Resolver

fun main(args: Array<String>) {
    val path = "D:\\Tyulenev\\Kotlin\\FootballCsvDemoStudents\\src\\main\\resources\\fakePlayers.csv"

    val players = CsvParser.parsePlayers(path)
    val resolver = Resolver(players)
    val rudestTeam = resolver.getTheRudestTeam()

    println("Количество игроков без агентства: ${resolver.getCountWithoutAgency()}")
    println("Защитник с наибольшим числом голов: ${resolver.getBestScorerDefender()}")
    println("Название позиции самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")
    println("Команда \"${rudestTeam.teamName}\" имеет наибольшее среднее число красных карточек: ${rudestTeam.getAverageRedCards()}")

    resolver.topExpensiveTeams()
}