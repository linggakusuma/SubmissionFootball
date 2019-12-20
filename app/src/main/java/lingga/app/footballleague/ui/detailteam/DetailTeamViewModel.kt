package lingga.app.footballleague.ui.detailteam

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lingga.app.footballleague.model.Teams
import lingga.app.footballleague.network.LeagueApi

class DetailTeamViewModel(val id: String) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _team = MutableLiveData<Teams>()
    val team: LiveData<Teams>
        get() = _team

    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    private var _statusText = MutableLiveData<Int>()
    val statusText: LiveData<Int>
        get() = _statusText

    init {
        getDetailTeam()
    }

    private fun getDetailTeam() {
        coroutineScope.launch {
            _status.value = View.VISIBLE
            _statusText.value = View.GONE
            val getDetailTeamDeferred = LeagueApi.retrofitService.getTeamAsync(id)
            try {
                val listTeam = getDetailTeamDeferred.await()
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
                _team.value = listTeam.teams[0]
            } catch (e: Exception) {
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
                _team.value = null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
