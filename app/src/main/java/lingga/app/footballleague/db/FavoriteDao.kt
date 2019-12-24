package lingga.app.footballleague.db

import androidx.lifecycle.LiveData
import androidx.room.*
import lingga.app.footballleague.model.Teams

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(teams: Teams)

    @Query("select * from teams_favorite_table")
    fun getTeams(): LiveData<List<Teams>>

    @Query("select * from teams_favorite_table")
    fun getTeamsList(): List<Teams>

    @Delete
    fun clear(teams: Teams)
}