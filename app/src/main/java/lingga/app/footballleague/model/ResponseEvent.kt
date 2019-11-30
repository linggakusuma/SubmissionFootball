package lingga.app.footballleague.model

data class ResponseEvent(
    val events: MutableList<Event>
)

data class ResponseSearchEvent(
    val event: MutableList<Event>
)