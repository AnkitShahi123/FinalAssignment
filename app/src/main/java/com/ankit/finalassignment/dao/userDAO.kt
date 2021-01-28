package com.ankit.finalassignmentproject.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ankit.finalassignmentproject.entity.User

@Dao
interface userDAO {
    @Insert
    suspend fun registerUser(user: User)

    @Query("select * from User where username=(:username) and password=(:password)")
    suspend fun checkUser(username: String, password: String): User
}