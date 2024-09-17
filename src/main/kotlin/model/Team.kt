package model

class Team(private val name: String, private val city: String, private val players: List<Player>) {

    fun getName(): String{
        return name
    }

    fun getCity(): String{
        return city
    }

    fun getPlayers(): List<Player>{
        return players
    }
}