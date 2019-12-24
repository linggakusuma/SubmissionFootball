package lingga.app.footballleague.ui.favorites.teamsfavorite

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lingga.app.footballleague.db.FavoriteDao
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class TeamsFavoriteViewModelFactory(
    private val dao: FavoriteDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamsFavoriteViewModel::class.java)) {
            return TeamsFavoriteViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}