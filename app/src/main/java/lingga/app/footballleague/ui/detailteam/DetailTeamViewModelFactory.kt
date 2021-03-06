package lingga.app.footballleague.ui.detailteam

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lingga.app.footballleague.db.FavoriteDao

@Suppress("UNCHECKED_CAST")
class DetailTeamViewModelFactory(
    private val id: String,
    private val dao: FavoriteDao,
    private val application: Application
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailTeamViewModel::class.java)) {
            return DetailTeamViewModel(id, dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}