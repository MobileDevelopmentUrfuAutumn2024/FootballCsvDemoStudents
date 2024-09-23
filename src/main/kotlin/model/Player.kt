package model

data class Player(
    val name: String, // имя
    val team: Team, // команда
    val position: Position, // позиция на поле
    val agency: String, // агенство
    val nationality: String, // национальность
    val transferCost: Int, // трансферная стоимость
    val participationCount: Int, // количество участий в матче
    val goalsCount: Int, // количество голов
    val assistsCount: Int, // количество передач
    val yellowCardsCount: Int, // желтые карточки
    val redCardsCount: Int, // красные карточки
)
