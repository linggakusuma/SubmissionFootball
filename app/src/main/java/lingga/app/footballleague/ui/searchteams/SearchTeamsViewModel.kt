package lingga.app.footballleague.ui.searchteams

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

class SearchTeamsViewModel(private val query: String, application: Application, val id: String) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _teams = MutableLiveData<List<Teams>>()
    val teams: LiveData<List<Teams>>
        get() = _teams

    private var _status = MutableLiveData<Int>()
    val status: LiveData<Int>
        get() = _status

    private var _statusText = MutableLiveData<Int>()
    val statusText: LiveData<Int>
        get() = _statusText

    init {
        getSearchTeams()
    }

    private fun getSearchTeams() {
        coroutineScope.launch {
            _status.value = View.VISIBLE
            _statusText.value = View.GONE
            val getSearchTeamsDeferred = LeagueApi.retrofitService.getSearchTeamsAsync(query)
            try {
                val listSearchTeams = getSearchTeamsDeferred.await()
                _teams.value = listSearchTeams.teams.filter {
                    it.strSport.equals("Soccer")
                    it.idLeague.equals(id)
                }
                if (listSearchTeams.teams[0].idLeague != id) {
                    _statusText.value = View.VISIBLE
                }
                _status.value = View.GONE
            } catch (e: Exception) {
                _teams.value = arrayListOf()
                _status.value = View.GONE
                _statusText.value = View.VISIBLE
            }
        }
    }
}
