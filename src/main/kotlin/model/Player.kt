package model

/**
 * Игрок
 * @param name имя
 * @param team команда
 * @param city город
 * @param position роль в команде
 * @param nationality национальность
 * @param agency агентство(может быть null)
 * @param transferCost стоимость трансфера
 * @param participations участие
 * @param goals голов
 * @param assists помощи
 * @param yellowCard жёлтые карточки
 * @param redCards красные карточки
 */
data class Player(
    val name: String,
    val team: String,
    val city: String,
    val position: Position,
    val nationality: String,
    val agency: String?,
    val transferCost: Long,
    val participations: Int,
    val goals: Int,
    val assists: Int,
    val yellowCard: Int,
    val redCards: Int
)
