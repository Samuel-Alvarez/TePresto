package com.example.tepresto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tepresto.data.local.dao.OcupacionDao
import com.example.tepresto.data.local.entity.OcupacionEntity


@Database(
    entities = [
        OcupacionEntity::class
    ],
    version = 1
)
abstract class TePrestoDb: RoomDatabase() {
    abstract val ocupacionDao: OcupacionDao
}