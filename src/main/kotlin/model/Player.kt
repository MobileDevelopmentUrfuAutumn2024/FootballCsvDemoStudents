package model

data class PersonData(val name: String, val nationality: String, val position: String)
data class TransferData(val agency: String, val cost: String)
data class StatisticsData(
    val participations: Int, val goals: Int, val assists: Int,
    val yellowCards: Int, val redCards: Int
)

class Player(
    private val personData: PersonData,
    private val transferData: TransferData,
    private val statisticsData: StatisticsData
) {
    fun getPersonData(): PersonData {
        return personData
    }

    fun getTransferData(): TransferData {
        return transferData
    }

    fun getStatisticsData(): StatisticsData {
        return statisticsData
    }
}
