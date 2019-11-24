package lingga.app.footballleague.ui.detailleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lingga.app.footballleague.model.DetailLeague
import lingga.app.footballleague.network.LeagueApi

class DetailLeagueViewModel(league: String) : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _detail = MutableLiveData<DetailLeague>()
    val detail: LiveData<DetailLeague>
        get() = _detail

    init {
        getLeagueApi(league)
    }

    private fun getLeagueApi(league: String) {
        coroutineScope.launch {
            val getDetailLeagueDeferred = LeagueApi.retrofitService.getDetailLeagueAsync(league)
            try {
                val listLeague = getDetailLeagueDeferred.await()
                val list = listLeague.leagues
                _detail.value = list[0]
            } catch (e: Exception) {
                _detail.value = null
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
