package com.dicoding.todoapp.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery

//TODO 2 : Define data access object (DAO)
interface TaskDao {

    @RawQuery(observedEntities = [Task :: class])
    fun getTasks(query: SupportSQLiteQuery): DataSource.Factory<Int, Task>

    @Query("SELECT * FROM tasks WHERE id= :taskId")
    fun getTaskById(taskId: Int): LiveData<Task>

    @Query("SELECT * FROM task WHERE completed = 0 ORDER BY dueDate ASC LIMIT 1 ")
    fun getNearestActiveTask(): Task

    @Insert
    suspend fun insertTask(task: Task): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertAll(vararg tasks: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("UPDATE task SET completed=:completed WHERE id=:taskId")
    suspend fun updateCompleted(taskId: Int, completed: Boolean)
    
}
