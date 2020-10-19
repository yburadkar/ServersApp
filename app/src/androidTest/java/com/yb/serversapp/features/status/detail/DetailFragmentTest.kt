package com.yb.serversapp.features.status.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yb.serversapp.R
import com.yb.serversapp.TestApp
import com.yb.serversapp.data.testdata.ServersData
import com.yb.serversapp.models.UiServerStatus
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {

    @Rule @JvmField
    val instantTaskRule = InstantTaskExecutorRule()
    private lateinit var scenario: FragmentScenario<DetailFragment>

    @Before
    fun setUp() {
        val bundle = DetailFragmentArgs(UiServerStatus(ServersData.serversList.first())).toBundle()
        scenario = launchFragmentInContainer<DetailFragment>(fragmentArgs = bundle, themeResId = R.style.AppTheme)
    }

    @Test
    fun onResume_checkIfAllViewsAreDisplayed() {
        onView(withId(R.id.server_name)).check(matches(isDisplayed()))
        onView(withId(R.id.status)).check(matches(isDisplayed()))
        onView(withId(R.id.server_url)).check(matches(isDisplayed()))
        onView(withId(R.id.response_time)).check(matches(isDisplayed()))
        onView(withId(R.id.response_class)).check(matches(isDisplayed()))
    }

    @Test
    fun onStart_checkIfAllViewsHaveCorrectData() {
        val server = UiServerStatus(ServersData.serversList.first())
        val context = getApplicationContext<TestApp>()
        onView(withId(R.id.server_name)).check(matches(withText(server.serverName)))
        onView(withId(R.id.status)).check(matches(withText(server.statusCode(context))))
        onView(withId(R.id.server_url)).check(matches(withText(server.url(context))))
        onView(withId(R.id.response_time)).check(matches(withText(server.time(context))))
        onView(withId(R.id.response_class)).check(matches(withText(server.classType(context))))
    }

}