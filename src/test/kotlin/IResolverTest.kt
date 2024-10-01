import model.Player
import model.Position
import model.Team
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import resolver.Resolver
import kotlin.test.Test

class IResolverTest {
    private val players = listOf(
        Player(
            name = "John Doe",
            team = Team("City A","Team A"),
            position = Position.DEFENDER,
            agency = "Agency X",
            nationality = "Germany",
            transferCost = 1000000,
            participationCount = 30,
            goalsCount = 5,
            assistsCount = 2,
            yellowCardsCount = 3,
            redCardsCount = 1
        ),
        Player(
            name = "Mike Smith",
            team = Team("City B","Team B"),
            position = Position.GOALKEEPER,
            agency = null,
            nationality = "Spain",
            transferCost = 1200000,
            participationCount = 25,
            goalsCount = 0,
            assistsCount = 0,
            yellowCardsCount = 1,
            redCardsCount = 0
        ),
        Player(
            name = "Alex Johnson",
            team = Team("City A","Team A"),
            position = Position.FORWARD,
            agency = "Agency Y",
            nationality = "France",
            transferCost = 1500000,
            participationCount = 28,
            goalsCount = 15,
            assistsCount = 5,
            yellowCardsCount = 2,
            redCardsCount = 0
        ),
        Player(
            name = "Chris Evans",
            team = Team("City C","Team C"),
            position = Position.MIDFIELD,
            agency = "Agency Z",
            nationality = "Italy",
            transferCost = 800000,
            participationCount = 32,
            goalsCount = 7,
            assistsCount = 10,
            yellowCardsCount = 5,
            redCardsCount = 2
        ),
        Player(
            name = "David Lee",
            team = Team("City B","Team B"),
            position = Position.DEFENDER,
            agency = null,
            nationality = "USA",
            transferCost = 900000,
            participationCount = 27,
            goalsCount = 3,
            assistsCount = 1,
            yellowCardsCount = 4,
            redCardsCount = 1
        )
    )

    private val resolver = Resolver(players)

    /**
     * Тест на получение кол-ва игроков без агентства
     */
    @Test
    fun testGetCountWithoutAgency() {
        val result = resolver.getCountWithoutAgency()
        assertEquals(2, result)
    }

    /**
     * Тест на получение лучшего защитника-бомбардира
     */
    @Test
    fun testGetBestScorerDefender() {
        val result = resolver.getBestScorerDefender()
        assertEquals("John Doe" to 5, result)
    }

    /**
     * Тест на срабатывание ошибки при получении лучшего защитника-бомбардира
     */
    @Test
    fun testGetBestScorerDefenderWithThrowException() {
        val resolver = getResolverWithEmptyTeam()
        val exception = assertThrows<IllegalStateException> {
            resolver.getBestScorerDefender()
        }
        assertEquals("No best scorer defender", exception.message)
    }

    /**
     * Тест на получение позиции самого дорогого немецкого игрока
     */
    @Test
    fun testGetTheExpensiveGermanPlayerPosition() {
        val result = resolver.getTheExpensiveGermanPlayerPosition()
        assertEquals("Защитник", result)
    }

    /**
     * Тест на срабатывание ошибки при получении позиции самого дорогого немецкого игрока
     */
    @Test
    fun testGetTheExpensiveGermanPlayerPositionWithException() {
        val resolver = getResolverWithEmptyTeam()
        val exception = assertThrows<IllegalStateException> {
            resolver.getTheExpensiveGermanPlayerPosition()
        }
        assertEquals("No German player found", exception.message)
    }

    /**
     * Тест на получение команды с наибольшим числом удалений на одного игрока
     */
    @Test
    fun testGetTheRudestTeam() {
        val result = resolver.getTheRudestTeam()
        assertEquals(Team("City C","Team C"), result)
    }

    /**
     * Тест на срабатывание ошибки при получении команды с наибольшим числом удалений на одного игрока
     */
    @Test
    fun testGetTheRudestTeamWithException() {
        val resolver = getResolverWithEmptyTeam()
        val exception = assertThrows<IllegalStateException> {
            resolver.getTheRudestTeam()
        }
        assertEquals("No rudest team found", exception.message)
    }

    /**
     * Тест на получение карты с долей игроков по позициям
     */
    @Test
    fun testGetShareOfPlayersByPosition() {
        val result = resolver.getShareOfPlayersByPosition()
        val expected = mapOf(
            Position.GOALKEEPER to 0.2,
            Position.DEFENDER to 0.4,
            Position.FORWARD to 0.2,
            Position.MIDFIELD to 0.2
        )
        assertEquals(expected, result)
    }

    private fun getResolverWithEmptyTeam(): Resolver {
        val players = listOf<Player>()
        return Resolver(players)
    }
}