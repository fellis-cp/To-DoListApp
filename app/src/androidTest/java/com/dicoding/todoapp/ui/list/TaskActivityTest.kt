package com.dicoding.todoapp.ui.list

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.add.AddTaskActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//TODO 16 : Write UI test to validate when user tap Add Task (+), the AddTaskActivity displayed

@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4ClassRunner::class)
class TaskActivityTest {

    @get:Rule
    val intentRule = IntentsTestRule(TaskActivity::class.java)

    @Test
    fun tappingAddTaskButton_opensAddTaskActivity() {

        Espresso.onView(ViewMatchers.withId(R.id.fab))
            .perform(ViewActions.click())


        Intents.intended(IntentMatchers.hasComponent(AddTaskActivity::class.java.name))
    }
}
