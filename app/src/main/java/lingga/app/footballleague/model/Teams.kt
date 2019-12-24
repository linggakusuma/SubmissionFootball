package lingga.app.footballleague.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams_favorite_table")
data class Teams(
    val strTeamBadge: String? = "",
    @PrimaryKey
    val idTeam: Int? = 0,
    val strTeam: String? = "",
    val intFormedYear: String? = "",
    val strStadium: String? = "",
    val strKeywords: String? = "",
    val strStadiumLocation: String? = "",
    val strDescriptionEN: String? = "",
    val strStadiumThumb: String? = "",
    val strStadiumDescription: String? = "",
    val intStadiumCapacity: String? = "",
    val strLeague: String? = "",
    val strSport: String? = "",
    val idLeague: String? = ""
)

data class ResponseTeams(
    val teams: MutableList<Teams>
)