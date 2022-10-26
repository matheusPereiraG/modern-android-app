package com.android.app.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsersDao {

    // user List
    @Query("select * from DatabaseUserDetails")
    fun getDatabaseUsers(): LiveData<List<DatabaseUserDetails>>

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<DatabaseUserListItem>)

    // single user
    @Query("select * from DatabaseUserDetails WHERE user LIKE :user")
    fun getUserDetails(user: String): LiveData<DatabaseUserDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetails(databaseUserDetails: DatabaseUserDetails)*/
}

@Database(entities = [DatabaseUserDetails::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao
}