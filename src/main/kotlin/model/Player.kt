package model

class Player(name: String, team: Team, position: Position, nationality: String, agency: String?,
             transfer_cost: String,	participations: Int, goals: Int, assists: Int, yellow_cards: Int,
             red_cards: Int){

    val name : String
    val team : Team
    val Position : Position
    val nationality: String
    val agency: String?
    val transfer_cost: String
    val participations: Int
    val goals: Int
    val assists: Int
    val yellow_cards: Int
    val red_cards: Int

    init{
        this.name = name
        this.team = team
        this.Position = position
        this.nationality = nationality
        this.agency = agency
        this.transfer_cost = transfer_cost
        this.participations = participations
        this.goals = goals
        this.assists = assists
        this.yellow_cards = yellow_cards
        this.red_cards = red_cards
    }
}
