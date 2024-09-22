import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import model.Player
import model.Position
import model.Team
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import resolver.ResolverImpl
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
class ResolverImplTest {
    @MockK
    var players: List<Player> = listOf(
        Player(
            name = "Iva Streich",
            team = Team("Nevada whales", "South Carolina"),
            position = Position.MIDFIELD,
            nationality = "Colombia",
            agency = "D'Amore LLC",
            transferCost = 75012006,
            participants = 22,
            goals = 19,
            assists = 6,
            yellowCards = 3,
            redCards = 7,
        ),
        Player(
            name = "Miss Buck Bradtke",
            team = Team("Nevada whales", "South Carolina"),
            position = Position.DEFENDER,
            nationality = "Brazil",
            agency = "D'Amore LLC",
            transferCost = 33850831,
            participants = 20,
            goals = 26,
            assists = 18,
            yellowCards = 6,
            redCards = 9,
        ),
        Player(
            name = "Everette Kovacek MD",
            team = Team("Wisconsin prophets", "Vermont"),
            position = Position.DEFENDER,
            nationality = "Palau",
            agency = "Walker and Sons",
            transferCost = 64396675,
            participants = 27,
            goals = 11,
            assists = 25,
            yellowCards = 0,
            redCards = 5,
        ),
        Player(
            name = "Ms. Adolph Hartmann",
            team = Team("North Carolina dolphins", "Tennessee"),
            position = Position.FORWARD,
            nationality = "Germany",
            agency = null,
            transferCost = 52944545,
            participants = 27,
            goals = 10,
            assists = 5,
            yellowCards = 5,
            redCards = 8,
        ),
    )

    private val resolver = ResolverImpl(players)

    @Test
    fun getCountWithoutAgency() {
        val result = resolver.getCountWithoutAgency()

        assertEquals(1, result)
    }

    @Test
    fun getBestScorerDefender() {
        val result = resolver.getBestScorerDefender()

        assertEquals("Miss Buck Bradtke", result.first)
        assertEquals(26, result.second)
    }

    @Test
    fun getTheRudestTeam() {
        val result = resolver.getTheRudestTeam()

        assertEquals(Team(name = "Nevada whales", city = "South Carolina"), result)
    }

    @Test
    fun getTheExpensiveGermanPlayerPosition() {
        val result = resolver.getTheExpensiveGermanPlayerPosition()

        assertEquals(Position.FORWARD.title, result)
    }
}