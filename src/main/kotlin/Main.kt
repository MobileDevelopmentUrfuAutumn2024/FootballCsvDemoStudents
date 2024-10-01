import model.Player
import model.Team

import resolver.IResolver
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartUtils
import org.jfree.chart.JFreeChart
import org.jfree.data.general.DefaultPieDataset
import java.io.File

fun main(args: Array<String>)   {
    var listPlayer = readFileLineByLineUsingForEachLine("src/main/resources/fakePlayers.csv")
    var getStats = Stats(listPlayer)
    var playerWithNoAgents = getStats.getCountWithoutAgency()
    var (defender, goals) = getStats.getBestScorerDefender()
    var russianNamePosGermanyPlayer = getStats.getTheExpensiveGermanPlayerPosition()
    var mostRedCardTeam = getStats.getTheRudestTeam()

    println("Количество игроков, интересы которых не представляет агенство - " + playerWithNoAgents)
    println("Автора наибольшего числа голов из числа защитников - " + defender + ", их количество - " + goals)
    println("Русское название позиции самого дорогого немецкого игрока - " + russianNamePosGermanyPlayer)
    println("Команда с наибольшим числом удалений на одного игрока - " + mostRedCardTeam.teamName)

    val positionCount = listPlayer.groupingBy { it.position }.eachCount()


    val dataset = DefaultPieDataset<String>()
    positionCount.forEach { (label, value) ->
        val realVal = value / listPlayer.count().toDouble()
        dataset.setValue(label, realVal)
    }

    var chart = ChartFactory.createPieChart(
        "Круговой график",
        dataset,
        true,
        true,
        false
    )

    val file = File("src/main/res.png")
    ChartUtils.saveChartAsPNG(file, chart, 600, 400)



}

fun readFileLineByLineUsingForEachLine(fileName: String): MutableList<Player> {
    var listPlayers: MutableList<Player> = mutableListOf<Player>()
    File(fileName).forEachLine {
        val info = it.split(";")
        if (info[0] != "Name") {
            listPlayers.add(Player(info[0],info[1],info[2],info[3],info[4], info[5], info[6].toInt(),info[7].toInt(),info[8].toInt(),info[9].toInt(),info[10].toInt(),info[11].toInt()))
        }
    }
    return listPlayers;
}

class Stats(var listPlayer: MutableList<Player> = mutableListOf<Player>()) : IResolver{
    val dictionaryOfPositionToRussia = mapOf("FORWARD" to "НАПАДАЮЩИЙ", "DEFENDER" to "ЗАЩИТНИК", "MIDFIELD" to "ПОЛУЗАЩИТНИК" , "GOALKEEPER" to "ВРАТАРЬ")

    override fun getCountWithoutAgency(): Int {
        var count = 0
        listPlayer.forEach {
            if (it.agent == ""){
                count++
            };
        }
        return count
    }

    override fun getBestScorerDefender(): Pair<String, Int> {
        var maxGoals = 0
        var maxAuthorGoals = "none"
        listPlayer.forEach {
            if ( it.position == "DEFENDER") {
                if (maxGoals <= it.scoreCount) {
                      maxGoals = it.scoreCount
                      maxAuthorGoals = it.name
                }
            }
        }

        return Pair(maxAuthorGoals, maxGoals)
    }

    override fun getTheExpensiveGermanPlayerPosition(): String {
        var maxPrice= 0
        var pos = "none"
        listPlayer.forEach {
            if ( it.country == "Germany") {
                if (maxPrice <= it.transferСost) {
                    maxPrice = it.transferСost
                    pos = it.position
                }
            }
        }
        return dictionaryOfPositionToRussia.get(pos).toString()
    }

    override fun getTheRudestTeam(): Team {

        var mapTeam = mutableMapOf<String, Int>()
        var mapTeamRedCard = mutableMapOf<String, Int>()

        listPlayer.forEach { player ->
            val teamKey = "${player.team.teamName}|${player.team.cityName}"
            if (mapTeam.containsKey(teamKey)) {
                mapTeam[teamKey] = mapTeam[teamKey]!! + 1
            } else {
                mapTeam[teamKey] = 1
            }
        }
        listPlayer.forEach { player ->
            val teamKey = "${player.team.teamName}|${player.team.cityName}"
            if (mapTeamRedCard.containsKey(teamKey)) {
                mapTeamRedCard[teamKey] = mapTeamRedCard[teamKey]!! + player.redCardCount
            } else {
                mapTeamRedCard[teamKey] = player.redCardCount
            }
        }
        var maxRedCardForPlayerTeam = "None"
        var maxValue = 0

        mapTeam.forEach {
            if ((mapTeamRedCard.get(it.key)!! / it.value) > maxValue) {
                maxRedCardForPlayerTeam = it.key
            }
        }

        var arrayRes = maxRedCardForPlayerTeam.split("|")

        return Team(arrayRes[0], arrayRes[1])



    }




}



