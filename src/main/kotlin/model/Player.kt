package model

import java.math.BigDecimal

class Player(
    val name: String,
    val team: Team,
    val city: String,
    val position: String,
    val nationality: String,
    val agency: String,
    val transferCost: Double,
    val participations: Int,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int
)
