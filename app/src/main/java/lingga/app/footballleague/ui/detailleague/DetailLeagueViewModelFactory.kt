package lingga.app.footballleague.ui.detailleague

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DetailLeagueViewModelFactory(private val league: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailLeagueViewModel::class.java)) {
            return DetailLeagueViewModel(league) as T
        }
        throw IllegalArgumentException("unknown")
    }
}