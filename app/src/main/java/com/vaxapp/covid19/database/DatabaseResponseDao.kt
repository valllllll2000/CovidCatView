package com.vaxapp.covid19.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseResponseDao {

    @Query("SELECT * FROM DatabaseResponse")
    fun getAll(): List<DatabaseResponse>

/*
    @Query("SELECT * FROM databaseresponse WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<DatabaseResponse>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
           "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): DatabaseResponse
*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseResponse: DatabaseResponse)

    @Delete
    fun delete(databaseResponses: DatabaseResponse)
}