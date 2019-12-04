package lingga.app.footballleague.ui.favorites.nextmatchfavorite

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import lingga.app.footballleague.db.database
import lingga.app.footballleague.model.Favorites
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class NextMatchFavoriteViewModel(application: Application, private val context: Context) :
    AndroidViewModel(application) {
    private var _lastFavorite = MutableLiveData<List<Favorites>>()
    val lastFavorite: LiveData<List<Favorites>>
        get() = _lastFavorite

    init {
        getNextFavorites()
    }

    private fun getNextFavorites() {
        context.database.use {
            val listFavorite =
                select(Favorites.TABLE_FAVORITE).whereArgs(
                    "(TYPE_MATCH = {type_match})",
                    "type_match" to Favorites.TYPE_NEXT
                )
                    .parseList(classParser<Favorites>())
            _lastFavorite.value = listFavorite
        }
    }
}
