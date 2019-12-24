package lingga.app.footballleague.ui.favorites.teamsfavorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Job
import lingga.app.footballleague.db.FavoriteDao
import lingga.app.footballleague.model.Teams

class TeamsFavoriteViewModel(dao: FavoriteDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()

    val teams: LiveData<List<Teams>> = dao.getTeams()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
