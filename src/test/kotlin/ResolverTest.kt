import junit.framework.TestCase.assertEquals
import model.GoalKeeperFromTransferCost
import model.Player
import model.Position
import model.Team
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import resolver.IResolver

class ResolverTest {

    private lateinit var resolver: IResolver
    private lateinit var listPlayers: List<Player>

    @Before
    fun setUp() {
        resolver = Mockito.mock(IResolver::class.java)
        listPlayers = listOf(
            Player(
                "Name",
                Team("name", "Ekb"),
                Position.DEFENDER,
                "Agency",
                "Nationality",
                100000,
                10,
                10,
                10,
                10,
                10
            )
        )

        listPlayers = listOf(
            Player(
                "Name2",
                Team("name2", "Ekb"),
                Position.GOALKEEPER,
                "Agency",
                "GERMANY",
                10000,
                10,
                10,
                10,
                1,
                1
            )
        )
    }

    @Test
    fun testGetCountWithoutAgency() {
        Mockito.`when`(resolver.getCountWithoutAgency(listPlayers)).thenReturn(1)
        assertEquals(1, resolver.getCountWithoutAgency(listPlayers))
    }

    @Test
    fun testGetBestScorerDefender() {
        Mockito.`when`(resolver.getBestScorerDefender(listPlayers)).thenReturn(Pair("Name2", 1))
        assertEquals(Pair("Name2", 1), resolver.getBestScorerDefender(listPlayers))
    }

    @Test
    fun testGetTheExpensiveGermanPlayerPosition() {
        Mockito.`when`(resolver.getTheExpensiveGermanPlayerPosition(listPlayers))
            .thenReturn("GOALKEEPER")
        assertEquals("GOALKEEPER", resolver.getTheExpensiveGermanPlayerPosition(listPlayers))
    }

    @Test
    fun testGetTheRudestTeam() {
        val rudeTeam = Team("Team1", "")
        Mockito.`when`(resolver.getTheRudestTeam(listPlayers)).thenReturn(rudeTeam)
        assertEquals(rudeTeam, resolver.getTheRudestTeam(listPlayers))
    }

    @Test
    fun testGetGoalKeeperFromTransferCost() {
        val goalKeeperList = listOf(
            GoalKeeperFromTransferCost(100000, 10),
            GoalKeeperFromTransferCost(10000, 10)
        )
        Mockito.`when`(resolver.getGoalKeeperFromTransferCost(listPlayers))
            .thenReturn(goalKeeperList)
        assertEquals(goalKeeperList, resolver.getGoalKeeperFromTransferCost(listPlayers))
    }
}