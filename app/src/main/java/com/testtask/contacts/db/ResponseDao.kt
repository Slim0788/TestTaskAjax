package com.testtask.contacts.db

import androidx.room.*
import com.testtask.contacts.api.dto.Result


@Dao
interface ResponseDao {

    @Query("SELECT * FROM Result")
    fun getAll(): List<Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<Result>)

    @Query("DELETE FROM Result")
    fun deleteAll()

    @Query("SELECT * FROM Result WHERE phone = :id")
    fun getByPhone(id: String): Result

    @Update
    fun update(result: Result)
}