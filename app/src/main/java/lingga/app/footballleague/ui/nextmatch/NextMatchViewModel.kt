package lingga.app.footballleague.ui.nextmatch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import lingga.app.footballleague.model.ResponseEvent
import lingga.app.footballleague.network.LeagueApi

class NextMatchViewModel(id: String, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _event = MutableLiveData<ResponseEvent>()
    val event: LiveData<ResponseEvent>
        get() = _event

    init {
        getNextMatch(id)
    }

    private fun getNextMatch(id: String) {
        coroutineScope.launch {
            val getNextMatchDeferred = LeagueApi.retrofitService.getNextMatchAsync(id)
            try {
                val listEvents = getNextMatchDeferred.await()
                _event.value = listEvents
            } catch (e: Exception) {
                _event.value = null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
