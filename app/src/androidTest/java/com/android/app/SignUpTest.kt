package com.android.app

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.app.di.NetworkModule
import com.android.app.views.authUser.LogInFragment
import com.android.app.views.authUser.SignUpFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * @Inject
lateinit var analyticsAdapter: AnalyticsAdapter

@Before
fun init() {
hiltRule.inject()
}
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class SignUpTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testTestBoxes() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<SignUpFragment> {
            viewLifecycleOwnerLiveData.observeForever {
                navController.setGraph(R.navigation.main)
                Navigation.setViewNavController(requireView(), navController)
            }
        }

        val name = "Random name"
        val email = "Invalid email"
        val password = "12345"

        val nameMatcher= onView(ViewMatchers.withId(R.id.name_et)).perform(typeText(name), closeSoftKeyboard())
        val emailMatcher=onView(ViewMatchers.withId(R.id.email_et)).perform(typeText(email), closeSoftKeyboard())
        val passwordMatcher=onView(ViewMatchers.withId(R.id.password_et)).perform(typeText(password), closeSoftKeyboard())

        assertEquals(TestUtils.getText(nameMatcher), name)
        assertEquals(TestUtils.getText(emailMatcher), email)
        assertEquals(TestUtils.getText(passwordMatcher), password)
    }


    @Test
    fun testBackBtn() {
        /*val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<SignUpFragment> {
            navController.setGraph(R.navigation.main)
            Navigation.setViewNavController(this.requireView(), navController)
        }

        pressBack()

        assertEquals(R.id.log_in, navController.currentDestination?.id)*/

        //Delete all logs first
        //Espresso.onView(withId(R.id.delete_logs)).perform(ViewActions.click())

        // Check Buttons fragment screen is displayed
        /*Espresso.onView(withId(R.id.textView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))*/
    }
}