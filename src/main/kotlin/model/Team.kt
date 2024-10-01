package model

class Team(val name:String,
           val players: MutableList<Player>) {

    override fun toString(): String {
        return name
    }

}