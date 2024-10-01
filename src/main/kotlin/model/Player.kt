package model

import resolver.IResolver

class Player {
    var name: String = "PlayerName"
    var team: Team = Team("teamName", "cityName");
    var position: String = "Position"
    var country: String = "Country"
    var agent: String = "Agent"
    var transferСost: Int = 0
    var playCount: Int = 0
    var scoreCount: Int = 0
    var assistCount: Int = 0
    var yellowCardCount: Int = 0
    var redCardCount: Int = 0

    constructor(_name: String, teamName: String, teamCity:String, _position: String, _country:String, _agent: String , _transferСost: Int, _playCount: Int, _scoreCount: Int, _assistCount: Int, _yellowCardCount: Int, _redCardCount: Int){
        name = _name
        team = Team(teamName, teamCity)
        position = _position
        country = _country
        agent = _agent
        transferСost = _transferСost
        playCount = _playCount
        scoreCount = _scoreCount
        assistCount = _assistCount
        yellowCardCount = _yellowCardCount
        redCardCount = _redCardCount
    }

};
