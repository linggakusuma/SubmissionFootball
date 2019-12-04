package lingga.app.footballleague.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import lingga.app.footballleague.model.Favorites
import org.jetbrains.anko.db.*

class FavoriteDatabase(context: Context) :
    ManagedSQLiteOpenHelper(context, Favorites.DB_NAME, null, 1) {
    companion object {
        private var instance: FavoriteDatabase? = null

        @Synchronized
        fun getInstance(context: Context): FavoriteDatabase {
            if (instance == null) {
                instance = FavoriteDatabase(context)
            }
            return instance as FavoriteDatabase
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            Favorites.TABLE_FAVORITE,
            true,
            Favorites.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorites.ID_EVENT to TEXT + UNIQUE,
            Favorites.STR_EVENT to TEXT,
            Favorites.DATE_EVENT to TEXT,
            Favorites.STR_TIME_LOCAL to TEXT,
            Favorites.HOME_TEAM_BADGE to TEXT,
            Favorites.STR_HOME_TEAM to TEXT,
            Favorites.AWAY_TEAM_BADGE to TEXT,
            Favorites.STR_AWAY_TEAM to TEXT,
            Favorites.INT_HOME_SCORE to TEXT,
            Favorites.INT_AWAY_SCORE to TEXT,
            Favorites.TYPE_MATCH to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, olderVer: Int, newVer: Int) {
        db?.dropTable(Favorites.TABLE_FAVORITE, true)
    }
}

val Context.database: FavoriteDatabase
    get() = FavoriteDatabase.getInstance(applicationContext)