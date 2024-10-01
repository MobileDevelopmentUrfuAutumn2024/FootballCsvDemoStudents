import parser.CsvParser
import resolver.Resolver

fun main() {
    val players = CsvParser.parse("src/main/resources/fakePlayers.csv")
    val resolver = Resolver(players)

    println("📊 Количество игроков без агенства: ${resolver.getCountWithoutAgency()}")

    if (resolver.getBestScorerDefender().isNotEmpty()) {
        val maxGoals = resolver.getBestScorerDefender().first().second
        println("\n🏆 Лучшие защитники по количеству голов ($maxGoals голов):")
        resolver.getBestScorerDefender().forEach { (name, _) ->
            println("   - $name")
        }
    } else {
        println("\n❗ Нет защитников для отображения.")
    }

    println("\n🔝 Позиция самого дорогого немецкого игрока: ${resolver.getTheExpensiveGermanPlayerPosition()}")

    println()

    println("🏅 Команда с наибольшим числом удалений на игрока: ${resolver.getTheRudestTeam().name}")

    generateCountryDistributionChart(players)
}
