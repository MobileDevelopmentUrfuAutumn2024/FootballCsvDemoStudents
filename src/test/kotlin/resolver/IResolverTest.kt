package resolver

import model.Player
import model.Team
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

/**
 * Тесты для интерфейса IResolver
 */
class IResolverTest {

    private val player1 = Player(
        name = "Player1",
        position = Player.Position.DEFENDER,
        nationality = "Germany",
        team = Team(name = "Team1", city = "City1"),
        transferCost = 50_000,
        agency = null,
        statistics = Player.Statistics(participations = 10, goals = 7, assists = 5, yellowCards = 2, redCards = 3)
    )
    private val player2 = Player(
        name = "Player2",
        position = Player.Position.FORWARD,
        nationality = "Brazil",
        team = Team(name = "Team2", city = "City2"),
        transferCost = 70_000,
        agency = "Agency2",
        statistics = Player.Statistics(participations = 10, goals = 5, assists = 5, yellowCards = 2, redCards = 1)
    )
    private val player3 = Player(
        name = "Player3",
        position = Player.Position.DEFENDER,
        nationality = "Spain",
        team = Team(name = "Team3", city = "City3"),
        transferCost = 40_000,
        agency = "Agency3",
        statistics = Player.Statistics(participations = 10, goals = 5, assists = 5, yellowCards = 2, redCards = 1)
    )
    private val player4 = Player(
        name = "Player4",
        position = Player.Position.GOALKEEPER,
        nationality = "Spain",
        team = Team(name = "Team1", city = "City1"),
        transferCost = 100_000,
        agency = "Agency4",
        statistics = Player.Statistics(participations = 10, goals = 7, assists = 5, yellowCards = 2, redCards = 3)
    )
    private val players = listOf(player1, player2, player3, player4)
    private val resolver = Resolver(players)

    /**
     * Тест на успешное получение количества игроков без агентства
     */
    @Test
    fun `return count of players without agency`() {
        val result = resolver.getCountWithoutAgency()
        assertEquals(1, result)
    }

    /**
     * Тест на успешное получение защитника с наибольшим количеством голов
     */
    @Test
    fun `return best scorer defender`() {
        val result = resolver.getBestScorerDefender()
        assertEquals("Player1" to 7, result)
    }

    /**
     * Тест на выбрасывание ошибки, при поиске защитника с наибольшим количеством голов, если таких нет
     */
    @Test
    fun `throw NoSuchElementException when no defender exists`() {
        val emptyPlayers = listOf<Player>()
        val emptyResolver = Resolver(emptyPlayers)
        val exception = assertThrows<NoSuchElementException> {
            emptyResolver.getBestScorerDefender()
        }
        assertEquals("Not found defender", exception.message)
    }

    /**
     * Тест на успешное получение позиции самого дорогого немецкого игрока
     */
    @Test
    fun `return most expensive German player's position`() {
        val result = resolver.getTheExpensiveGermanPlayerPosition()
        assertEquals("Защитник", result)
    }

    /**
     * Тест на выбрасывание ошибки, при поиске позиции самого дорогого немецкого игрока, если таких нет
     */
    @Test
    fun `throw NoSuchElementException when no German player exists`() {
        val nonGermanPlayers = listOf(player2, player3)
        val nonGermanResolver = Resolver(nonGermanPlayers)
        val exception = assertThrows<NoSuchElementException> {
            nonGermanResolver.getTheExpensiveGermanPlayerPosition()
        }
        assertEquals("Not found German player position", exception.message)
    }

    /**
     * Тест на корректное получение команды с наибольшим средним количеством красных карточек на игрока
     */
    @Test
    fun `return rudest team`() {
        val result = resolver.getTheRudestTeam()
        assertEquals(Team("Team1", "City1"), result)
    }

    /**
     * Тест на выбрасывание ошибки, при поиске команды с наибольшим средним количеством красных карточек на игрока,
     * если таких нет
     */
    @Test
    fun `throw NoSuchElementException when no rudest team exists`() {
        val emptyPlayers = listOf<Player>()
        val emptyResolver = Resolver(emptyPlayers)
        val exception = assertThrows<NoSuchElementException> {
            emptyResolver.getTheRudestTeam()
        }
        assertEquals("Not found rudest team", exception.message)
    }

    /**
     * Тест на успешное получение доли игроков из разных стран
     */
    @Test
    fun `return share of players from different countries`() {
        val result = resolver.getSharePlayersDifferentCountries()
        assertEquals(3, result.size)
        assertEquals(0.25, result["Germany"])
        assertEquals(0.25, result["Brazil"])
        assertEquals(0.5, result["Spain"])
    }

}