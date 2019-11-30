package lingga.app.footballleague.ui.search

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

class SearchViewModel(query: String, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _event = MutableLiveData<List<Event>>()
    val event: LiveData<List<Event>>
        get() = _event

    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    private var _statusText = MutableLiveData<Int>()
    val statusText: LiveData<Int>
        get() = _statusText


    init {
        getSearch(query)
    }

    private fun getSearch(query: String) {
        coroutineScope.launch {
            val getSearchDeferred = LeagueApi.retrofitService.getSearchAsync(query)
            try {
                _status.value = View.VISIBLE
                _statusText.value = View.GONE
                val listSearch = getSearchDeferred.await()
                listSearch.event.forEach {
                    val getTeamsHomeDeferred = LeagueApi.retrofitService.getTeamAsync(it.idHomeTeam)
                    val listTeam = getTeamsHomeDeferred.await()
                    it.homeTeamBadge = listTeam.teams[0].strTeamBadge

                    val getTeamsAwayDeferred = LeagueApi.retrofitService.getTeamAsync(it.idAwayTeam)
                    val listTeamAway = getTeamsAwayDeferred.await()
                    it.awayTeamBadge = listTeamAway.teams[0].strTeamBadge
                }
                _event.value = listSearch.event.filter {
                    it.strSport.equals("Soccer")
                }
                _status.value = View.GONE
            } catch (e: Exception) {
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
                _event.value = arrayListOf()
            }
        }
    }
}