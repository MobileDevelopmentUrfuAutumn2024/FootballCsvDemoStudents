package model

data class Team(
    val name: String,
    val city: String,
    var redCardsNum: Int = 0
){
}