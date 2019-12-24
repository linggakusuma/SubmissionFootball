package lingga.app.footballleague.ui.searchteams

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class SearchTeamsViewModelFactory(
    private val query: String,
    private val application: Application,
    private val id: String
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchTeamsViewModel::class.java)) {
            return SearchTeamsViewModel(query, application, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}