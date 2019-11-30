package lingga.app.footballleague.ui.lastmatch

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class LastMatchViewModelFactory(private val id: String, private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LastMatchViewModel::class.java)) {
            return LastMatchViewModel(id, application) as T
        }
        throw IllegalArgumentException("Unknown")
    }
}