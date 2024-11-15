package com.example.mysteryShopper.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StatusEntity::class], version = 1, exportSchema = false)
abstract class OfflineStatusDataBase : RoomDatabase() {

    abstract fun offlineStatusDao() : OfflineStatusDao


}