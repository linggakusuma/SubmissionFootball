package lingga.app.footballleague.model

data class Event(
    val idEvent: String?,
    val strEvent: String?,
    val dateEvent: String?,
    val strTimeLocal: String?,
    val idHomeTeam: String?,
    val idAwayTeam: String?,
    val strHomeTeam: String?,
    val strAwayTeam: String?,
    val intHomeScore: String?,
    val intAwayScore: String?,
    val strHomeGoalDetails: String?,
    val strAwayGoalDetails: String?,
    var strHomeRedCards: String?,
    var strAwayRedCards: String?,
    var strHomeYellowCards: String?,
    var strAwayYellowCards: String?,
    val strHomeLineupGoalkeeper: String?,
    val strAwayLineupGoalkeeper: String?,
    val strAwayLineupDefense: String?,
    val strHomeLineupDefense: String?,
    val strAwayLineupMidfield: String?,
    val strHomeLineupMidfield: String?,
    val strAwayLineupForward: String?,
    val strHomeLineupForward: String?,
    val strSport: String?,
    var homeTeamBadge: String?,
    var awayTeamBadge: String?
) {
    fun getValueHomeRedCard(): String? {
        if (strHomeRedCards == "") {
            strHomeRedCards = "0"
        }
        return strHomeRedCards
    }

    fun getValueAwayRedCard(): String? {
        if (strAwayRedCards == "") {
            strAwayRedCards = "0"
        }
        return strAwayRedCards
    }

    fun getValueHomeYellowCard(): String? {
        if (strHomeYellowCards == "") {
            strHomeYellowCards = "0"
        }
        return strHomeYellowCards
    }

    fun getValueAwayYellowCard(): String? {
        if (strAwayYellowCards == "") {
            strAwayYellowCards = "0"
        }
        return strAwayYellowCards
    }
}