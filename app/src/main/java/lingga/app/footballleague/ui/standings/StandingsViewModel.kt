package lingga.app.footballleague.ui.standings

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lingga.app.footballleague.model.Standings
import lingga.app.footballleague.network.LeagueApi

class StandingsViewModel(
    val id: String,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _table = MutableLiveData<List<Standings>>()
    val table: LiveData<List<Standings>>
        get() = _table

    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    private var _statusText = MutableLiveData<Int>()
    val statusText: LiveData<Int>
        get() = _statusText

    init {
        getStandings()
    }

    private fun getStandings() {
        coroutineScope.launch {
            _status.value = View.VISIBLE
            _statusText.value = View.GONE
            val getStandingsDeferred = LeagueApi.retrofitService.getStandingsAsync(id)
            try {
                val listTable = getStandingsDeferred.await()
                listTable.table?.forEach {
                    val getTeamsHomeDeferred = LeagueApi.retrofitService.getTeamAsync(it.teamid)
                    val listTeam = getTeamsHomeDeferred.await()
                    it.teamBadge = listTeam.teams[0].strTeamBadge
                }
                _table.value = listTable.table
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
            } catch (e: Exception) {
                _table.value = arrayListOf()
                _status.value = View.GONE
                _statusText.value = View.GONE
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
