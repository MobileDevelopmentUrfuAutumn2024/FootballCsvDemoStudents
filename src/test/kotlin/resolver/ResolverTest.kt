package resolver

import model.Player
import model.Position
import model.Team
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

/**
 * Тест resolver
 */
class ResolverTest {
    private lateinit var resolver: IResolver
    private lateinit var players: List<Player>

    /**
     * предустановка
     */
    @BeforeEach
    fun setup(){
        resolver = Mockito.mock(IResolver::class.java)
        players = listOf(
            Player(
                name = "Иван",
                team = "урфу",
                city = "Москва",
                position = Position.DEFENDER,
                nationality = "Немец",
                agency = "риттель",
                transferCost = 123123123,
                participations = 15,
                goals = 1,
                assists = 2,
                yellowCard = 100,
                redCards = 50
            ),
            Player(
                name = "Вадим",
                team = "урфу",
                city = "Екатеринбург",
                position = Position.MIDFIELD,
                nationality = "Русский",
                agency = null,
                transferCost = 0,
                participations = 0,
                goals = 0,
                assists = 0,
                yellowCard = 0,
                redCards = 0
            ),
            Player(
                name = "Маша",
                team = "синх",
                city = "",
                position = Position.OTHER,
                nationality = "Американка",
                agency = "",
                transferCost = 15,
                participations = 15,
                goals = 15,
                assists = 15,
                yellowCard = 15,
                redCards = 15
            )
        )
    }

    /**
     * Тест на получение количества игроков, интересы которых не представляет агенство
     */
    @Test
    fun getCountWithoutAgency() {
        `when`(resolver.getCountWithoutAgency()).thenReturn(1)
        assertEquals(1, resolver.getCountWithoutAgency())
    }

    /**
     * Тест на автора наибольшего числа голов из числа защитников и их количество.
     */
    @Test
    fun getBestScorerDefender() {
        `when`(resolver.getBestScorerDefender()).thenReturn(Pair("Иван",1))
        assertEquals(Pair("Иван",1), resolver.getBestScorerDefender())
    }

    /**
     * Тест на получение русского названия позиции самого дорогого немецкого игрока.
     */
    @Test
    fun getTheExpensiveGermanPlayerPosition() {
        `when`(resolver.getTheExpensiveGermanPlayerPosition()).thenReturn("Нападающий")
        assertEquals("Нападающий", resolver.getTheExpensiveGermanPlayerPosition())
    }

    /**
     * Тест на получение команды с наибольшим числом удалений на одного игрока.
     */
    @Test
    fun getTheRudestTeam() {
        `when`(resolver.getTheRudestTeam()).thenReturn(Team("урфу", players))
        assertEquals(Team("урфу", players), resolver.getTheRudestTeam())
    }
}