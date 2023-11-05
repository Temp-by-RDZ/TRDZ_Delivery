package com.trdz.mydelivery.model.source_room.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EntityCategory::class, EntityMeals::class], version = 1)
abstract class DataBase: RoomDatabase() {
	abstract fun orderDao(): OrderDao
}