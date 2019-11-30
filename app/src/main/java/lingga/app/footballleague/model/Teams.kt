package lingga.app.footballleague.model

data class Teams(
    val strTeamBadge: String?
)

data class ResponseTeams(
    val teams: MutableList<Teams>
)