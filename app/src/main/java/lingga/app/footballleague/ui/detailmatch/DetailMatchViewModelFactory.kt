package lingga.app.footballleague.ui.detailmatch

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class DetailMatchViewModelFactory(private val id: String, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailMatchViewModel::class.java)) {
            return DetailMatchViewModel(id,context) as T
        }
        throw IllegalArgumentException("Unknown")
    }
}