package com.testtask.contacts.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.testtask.contacts.api.dto.Result

@Database(entities = [Result::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun responseDao(): ResponseDao
}