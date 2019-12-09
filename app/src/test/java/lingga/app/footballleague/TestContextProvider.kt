package lingga.app.footballleague

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import lingga.app.footballleague.utils.CoroutineContextProvider
import kotlin.coroutines.CoroutineContext


class TestContextProvider : CoroutineContextProvider() {
    @ExperimentalCoroutinesApi
    override val main: CoroutineContext = Dispatchers.Unconfined
}