package lingga.app.footballleague.ui.detailmatch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class DetailMatchViewModelFactory(private val id: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailMatchViewModel::class.java)) {
            return DetailMatchViewModel(id) as T
        }
        throw IllegalArgumentException("Unknown")
    }
}