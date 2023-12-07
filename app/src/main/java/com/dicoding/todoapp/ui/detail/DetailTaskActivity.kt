package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var viewModel : DetailTaskViewModel
    private lateinit var delButton: Button
    private lateinit var desc: TextView
    private lateinit var title: TextView
    private lateinit var dueDate: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        delButton = findViewById(R.id.btn_delete_task)
        desc = findViewById(R.id.detail_ed_description)
        title = findViewById(R.id.detail_ed_title)
        dueDate = findViewById(R.id.detail_ed_due_date)

        //TODO 11 : Show detail task and implement delete action

        val factory = ViewModelFactory.getInstance(this)
        viewModel  = ViewModelProvider(this , factory)[DetailTaskViewModel::class.java]

        val taskId = intent.getIntExtra(TASK_ID , 0 )
        val myTask = getDataTask(taskId)
        myTask.observe(this, Observer { myTask ->
            delButton.setOnClickListener{
                viewModel.deleteTask(myTask)
            }
            desc.text = myTask.description
            dueDate.text = DateConverter.convertMillisToString(myTask.dueDateMillis)
            title.text = myTask.title

        })




    }



    private fun getDataTask(taskId: Int) : LiveData<Task> {
        viewModel.setTaskId(taskId)
        val dataTask = viewModel.task
        Log.d("Test" , dataTask.toString())
        return dataTask
    }
}