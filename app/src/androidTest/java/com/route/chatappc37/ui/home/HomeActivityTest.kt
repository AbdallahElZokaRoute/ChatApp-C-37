package com.route.chatappc37.ui.home

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.route.chatappc37.R
import com.route.chatappc37.ui.addRoom.AddRoomActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    // Espresso UI Testing
    // Espresso -> UI Testings (Unit Test on a real device )

    private lateinit var scenario: ActivityScenario<HomeActivity>
    //Scrum (Agile)
    //1- Stand up meetings Every Day for 10 mins maximum
    //2- Planning meeting -> Tasks , Assigned , Iteration
    //3- Retrospective Meetings -> V , X .
    //4- Iteration
    // Agile Concepts
    // 1- Cross Functional Team
    // 2- Self-Organized Team

    /**
     * 0- Create Linkedin Account
     * 1- Share video-shots about apps you done
     * 2- Always Learn more (About new topics)
     * 3- Git Commit + Push + Fork + Pull Requests
     * 4- Unit Testing -> Quality of code
     * Old vs new
     * Old (if you previously Studied )Then Search about its cheat sheets-> APIs , Retrofit , Room
     * New (Take You Time)
     * Important to have :
     *      OOP
     *      Design Patterns
     *      SOLID
     * Important TO Have (Android):
     *      Retrofit + APIS
     *      Room
     *      (Channels vs Flow) Flow + Coroutines
     *      Dependency Injection (Dagger Hilt)
     *      Repo Pattern
     *      Clean Architecture
     *      MVVM -> Model-View-ViewModel
     *
     * Nice to have :
     *      Jetpack Compose
     *      Unit Testing
     *      Clean Architecture
     *      MVI -> Model-View-Intent
     * LinkedIN :
     *      Graduated From **** University
     *  Intern at Route
     *      from *** to ****
     *  Video-shot about Islami app
     *  News App -> APIs using Retrofit
     *              Clean Architecture
     *              Coroutines
     *              Flow
     *              Unit Testing
     *  Add more connections
     *  500 -> Add More knowledge
     *      -> Share knowledge
     *
     *
     *
     *
     *
     * 2- Apply for a job -> Freelance
     *                    -> At a specific company
     *
     *
     *
     */

    @Before
    fun setUp() {
        scenario = launchActivity()
        scenario.moveToState(Lifecycle.State.RESUMED)
        Intents.init()
    }

    @Test
    fun whenClickingOnFAB_NavigateTo_AddRoomActivity() {
        //Arrange
        val roomName = "Flutter"
        val roomDesc = "Flutter Description Description "

        // Automation

        // Unit Testings X
        // Manual Testing -> Run Emulator -> Run test Cases manually
        // Over Headache
        // Quality of Code ,
        // Faster Than Manual Testing


        //Act
        Espresso.onView(withId(R.id.add_room)).perform(click())

        //Assertion
        Intents.intended(hasComponent(AddRoomActivity::class.java.name))

        Espresso.onView(withId(R.id.room_name_edit_text)).perform(typeText(roomName))
        Espresso.onView(withId(R.id.desc_edit_text)).perform(typeText(roomDesc))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.add_room_button)).perform(click())

        Espresso.onView(withId(R.id.rooms_recylcer_view))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rooms_recylcer_view))
    }

    @After
    fun tearDown() {
        //scenario.close()
        Intents.release()
    }


//    @get:Rule
//    val activityScenarioRule = ActivityScenarioRule(HomeActivity::class.java)

//    private lateinit var scenario: ActivityScenario<HomeActivity>

//    @Before
//    fun setUp() {
//        scenario = launchActivity()
//        scenario.moveToState(Lifecycle.State.RESUMED)
//        Intents.init()
//    }
//
//    @Test
//    fun onFabClickShouldNavigateToAddRoomScreen() {
//        Espresso.onView(ViewMatchers.withId(R.id.add_room)).perform(click())
//
//
//        Intents.intended(hasComponent(AddRoomActivity::class.java.name))
//    }
//
//    @After
//    fun tearDown() {
//        Intents.release()
//    }

}