package lingga.app.footballleague

import android.widget.AutoCompleteTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import lingga.app.footballleague.R.id.search
import org.junit.Rule
import org.junit.Test

class SearchInstrumentationTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testBehaviour() {
        onView(withId(search)).check(matches(isDisplayed())).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(
            typeText("Arsenal"),
            closeSoftKeyboard(), pressImeActionButton()
        )
        Thread.sleep(30000)
        onView(withId(search)).check(matches(isDisplayed())).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(
            clearText()
        )
        onView(withId(search)).check(matches(isDisplayed())).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(
            typeText("Chelsea"),
            closeSoftKeyboard(), pressImeActionButton()
        )
        Thread.sleep(30000)
        onView(withId(search)).check(matches(isDisplayed())).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(
            clearText()
        )
        onView(withId(search)).check(matches(isDisplayed())).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(
            typeText("Barcelona"),
            closeSoftKeyboard(), pressImeActionButton()
        )
        Thread.sleep(30000)
    }
}