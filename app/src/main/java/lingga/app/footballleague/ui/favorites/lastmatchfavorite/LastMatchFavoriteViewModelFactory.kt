package lingga.app.footballleague.ui.favorites.lastmatchfavorite

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class LastMatchFavoriteViewModelFactory(
    private val application: Application,
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LastMatchFavoriteViewModel::class.java)) {
            return LastMatchFavoriteViewModel(application, context) as T
        }
        throw IllegalArgumentException("Unknown")
    }
}