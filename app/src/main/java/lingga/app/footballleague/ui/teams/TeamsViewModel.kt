package lingga.app.footballleague.ui.teams

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lingga.app.footballleague.model.Teams
import lingga.app.footballleague.network.LeagueApi

class TeamsViewModel(
    val id: String,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _teams = MutableLiveData<List<Teams>>()
    val teams: LiveData<List<Teams>>
        get() = _teams

    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    init {
        getAllTeams()
    }

    private fun getAllTeams() {
        coroutineScope.launch {
            _status.value = View.VISIBLE
            val getAllTeamsDeferred = LeagueApi.retrofitService.getAllTeamsAsync(id)
            try {
                val listTeams = getAllTeamsDeferred.await()
                _teams.value = listTeams.teams
                _status.value = View.GONE
            } catch (e: Exception) {
                _status.value = View.GONE
                _teams.value = arrayListOf()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
