package lingga.app.footballleague.ui.nextmatch

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class NextMatchViewModelFactory(private val id: String, private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NextMatchViewModel::class.java)) {
            return NextMatchViewModel(id, application) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel")
    }
}