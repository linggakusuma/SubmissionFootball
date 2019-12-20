package lingga.app.footballleague.model

data class Teams(
    val strTeamBadge: String?,
    val idTeam: String?,
    val strTeam: String?,
    val intFormedYear: String?,
    val strStadium: String?,
    val strKeywords: String?,
    val strStadiumLocation: String?,
    val strDescriptionEN: String?,
    val strStadiumThumb: String?,
    val strStadiumDescription: String?,
    val intStadiumCapacity: String,
    val strLeague: String?
)

data class ResponseTeams(
    val teams: MutableList<Teams>
)