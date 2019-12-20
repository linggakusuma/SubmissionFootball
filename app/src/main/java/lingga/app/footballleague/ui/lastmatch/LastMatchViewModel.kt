package lingga.app.footballleague.ui.lastmatch

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lingga.app.footballleague.model.Event
import lingga.app.footballleague.network.LeagueApi
import lingga.app.footballleague.utils.CoroutineContextProvider

class LastMatchViewModel(
    val id: String, application: Application,
    val context: CoroutineContextProvider = CoroutineContextProvider()
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + context.main)

    private var _eventLastMatch = MutableLiveData<List<Event>>()
    val eventLastMatch: LiveData<List<Event>>
        get() = _eventLastMatch

    private var _eventNextMatch = MutableLiveData<List<Event>>()
    val eventNextMatch: LiveData<List<Event>>
        get() = _eventNextMatch

    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    private var _statusText = MutableLiveData<Int>()
    val statusText: LiveData<Int>
        get() = _statusText

    init {
        getLastMatch()
    }

    fun getLastMatch() {
        coroutineScope.launch {
            val getLastMatchDerred = LeagueApi.retrofitService.getLastMatchAsync(id)
            val getNextMatchDeffered = LeagueApi.retrofitService.getNextMatchAsync(id)
            try {
                _status.value = View.VISIBLE
                _statusText.value = View.INVISIBLE
                val listEvent = getLastMatchDerred.await()
                val listEventNext = getNextMatchDeffered.await()
                listEvent.events.forEach {
                    val getTeamsHomeDeferred = LeagueApi.retrofitService.getTeamAsync(it.idHomeTeam)
                    val listTeam = getTeamsHomeDeferred.await()
                    it.homeTeamBadge = listTeam.teams[0].strTeamBadge

                    val getTeamsAwayDeferred = LeagueApi.retrofitService.getTeamAsync(it.idAwayTeam)
                    val listTeamAway = getTeamsAwayDeferred.await()
                    it.awayTeamBadge = listTeamAway.teams[0].strTeamBadge
                }

                listEventNext.events.forEach {
                    val getTeamsHomeDeferred = LeagueApi.retrofitService.getTeamAsync(it.idHomeTeam)
                    val listTeam = getTeamsHomeDeferred.await()
                    it.homeTeamBadge = listTeam.teams[0].strTeamBadge

                    val getTeamsAwayDeferred = LeagueApi.retrofitService.getTeamAsync(it.idAwayTeam)
                    val listTeamAway = getTeamsAwayDeferred.await()
                    it.awayTeamBadge = listTeamAway.teams[0].strTeamBadge
                }
                _eventLastMatch.value = listEvent.events
                _eventNextMatch.value = listEventNext.events
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
            } catch (e: Exception) {
                _status.value = View.GONE
                _statusText.value = View.GONE
                _eventLastMatch.value = arrayListOf()
                _eventNextMatch.value = arrayListOf()
            }
        }
    }
}
