package model

class Team(
    val teamName: String,
    val players: List<Player>
) {
    fun getAverageRedCards(): Double {
        return players.map { it.redCards }.average()
    }

    fun getTotalTransferCost(): Long {
        return players.sumOf { it.transferCost }
    }
}