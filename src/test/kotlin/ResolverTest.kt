import junit.framework.TestCase.assertEquals
import model.ForwardFromTransferCost
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
        Mockito.`when`(resolver.getCountWithoutAgency()).thenReturn(1)
        assertEquals(1, resolver.getCountWithoutAgency())
    }

    @Test
    fun testGetBestScorerDefender() {
        Mockito.`when`(resolver.getBestScorerDefender()).thenReturn(Pair("Name2", 1))
        assertEquals(Pair("Name2", 1), resolver.getBestScorerDefender())
    }

    @Test
    fun testGetTheExpensiveGermanPlayerPosition() {
        Mockito.`when`(resolver.getTheExpensiveGermanPlayerPosition())
            .thenReturn("GOALKEEPER")
        assertEquals("GOALKEEPER", resolver.getTheExpensiveGermanPlayerPosition())
    }

    @Test
    fun testGetTheRudestTeam() {
        val rudeTeam = Team("Team1", "")
        Mockito.`when`(resolver.getTheRudestTeam()).thenReturn(rudeTeam)
        assertEquals(rudeTeam, resolver.getTheRudestTeam())
    }

    @Test
    fun testGetGoalKeeperFromTransferCost() {
        val goalKeeperList = listOf(
            ForwardFromTransferCost(100000, 10),
            ForwardFromTransferCost(10000, 10)
        )
        Mockito.`when`(resolver.getForwardFromTransferCost())
            .thenReturn(goalKeeperList)
        assertEquals(goalKeeperList, resolver.getForwardFromTransferCost())
    }
}