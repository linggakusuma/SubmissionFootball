package lingga.app.footballleague.ui.nextmatch

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lingga.app.footballleague.model.Event
import lingga.app.footballleague.network.LeagueApi

class NextMatchViewModel(val id: String, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _event = MutableLiveData<List<Event>>()
    val event: LiveData<List<Event>>
        get() = _event

    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    init {
        getNextMatch()
    }

    private fun getNextMatch() {
        coroutineScope.launch {
            val getNextMatchDeferred = LeagueApi.retrofitService.getNextMatchAsync(id)
            try {
                _status.value = View.VISIBLE
                val listEvents = getNextMatchDeferred.await()
                listEvents.events.forEach {
                    val getTeamsHomeDeferred = LeagueApi.retrofitService.getTeamAsync(it.idHomeTeam)
                    val listTeam = getTeamsHomeDeferred.await()
                    it.homeTeamBadge = listTeam.teams[0].strTeamBadge

                    val getTeamsAwayDeferred = LeagueApi.retrofitService.getTeamAsync(it.idAwayTeam)
                    val listTeamAway = getTeamsAwayDeferred.await()
                    it.awayTeamBadge = listTeamAway.teams[0].strTeamBadge
                }
                _event.value = listEvents.events
                _status.value = View.GONE

            } catch (e: Exception) {
                _status.value = View.GONE
                _event.value = arrayListOf()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}