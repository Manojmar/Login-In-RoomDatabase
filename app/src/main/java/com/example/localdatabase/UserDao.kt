package com.example.localdatabase

import androidx.room.*

@Dao
interface UserDao {
//    insert
    @Insert
    fun insert(user: User)

//    update
    @Update
    fun update(user: User)

//    delete
    @Delete
    fun delete(user: User)

//    fetch
    @Query("SELECT * FROM user_Table")
    fun getAllUser():List<User>


}