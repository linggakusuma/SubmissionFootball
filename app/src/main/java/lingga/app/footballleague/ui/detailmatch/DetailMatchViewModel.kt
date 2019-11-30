package lingga.app.footballleague.ui.detailmatch

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lingga.app.footballleague.model.Event
import lingga.app.footballleague.network.LeagueApi

class DetailMatchViewModel(id: String) : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _detailMatch = MutableLiveData<Event>()
    val detailMatch: LiveData<Event>
        get() = _detailMatch

    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    private var _statusText = MutableLiveData<Int>()
    val statusText: LiveData<Int>
        get() = _statusText

    private var _statusImage = MutableLiveData<Int>()
    val statusImage: LiveData<Int>
        get() = _statusImage

    init {
        getDetailMatch(id)
    }

    private fun getDetailMatch(id: String) {
        coroutineScope.launch {
            val getDetailMatchDeferred = LeagueApi.retrofitService.getDetailMatchAsync(id)
            try {
                _status.value = View.VISIBLE
                _statusText.value = View.GONE
                _statusImage.value = View.GONE
                val listDetail = getDetailMatchDeferred.await()
                val list = listDetail.events
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
                _statusImage.value = View.VISIBLE
                list.forEach {
                    val getTeamsHomeDeferred = LeagueApi.retrofitService.getTeamAsync(it.idHomeTeam)
                    val listTeam = getTeamsHomeDeferred.await()
                    it.homeTeamBadge = listTeam.teams[0].strTeamBadge

                    val getTeamsAwayDeferred = LeagueApi.retrofitService.getTeamAsync(it.idAwayTeam)
                    val listTeamAway = getTeamsAwayDeferred.await()
                    it.awayTeamBadge = listTeamAway.teams[0].strTeamBadge
                }
                _detailMatch.value = list[0]
            } catch (e: Exception) {
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
                _statusImage.value = View.VISIBLE
                _detailMatch.value = null
            }
        }
    }
}
