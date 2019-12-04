package lingga.app.footballleague.model

data class Favorites(
    val id: Long?,
    val idEvent: String?,
    val strEvent: String?,
    val dateEvent: String?,
    val strTimeLocal: String?,
    val homeTeamBadge: String?,
    val strHomeTeam: String?,
    val awayTeamBadge: String?,
    val strAwayTeam: String?,
    val intHomeScore: String?,
    val intAwayScore: String?,
    val typeMatch: String?
) {
    companion object {
        const val DB_NAME = "footballleague.db"
        const val TABLE_FAVORITE = "table_favorite"
        const val ID = "id"
        const val ID_EVENT = "id_event"
        const val STR_EVENT = "str_event"
        const val DATE_EVENT = "date_event"
        const val STR_TIME_LOCAL = "str_time_local"
        const val HOME_TEAM_BADGE = "home_team_badge"
        const val STR_HOME_TEAM = "str_home_team"
        const val AWAY_TEAM_BADGE = "away_team_badge"
        const val STR_AWAY_TEAM = "str_away_team"
        const val INT_HOME_SCORE = "int_home_score"
        const val INT_AWAY_SCORE = "int_away_score"
        const val TYPE_MATCH = "type_match"
        const val TYPE_LAST = "type_last"
        const val TYPE_NEXT = "type_next"
    }
}