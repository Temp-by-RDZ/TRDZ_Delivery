package com.trdz.mydelivery.model.source_room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class EntityMeals(
	@PrimaryKey()
	@ColumnInfo(name = PRIMARY_KEY)
	val id: String,
	val name: String,
	val pic: String,
	val category: String
) {
	companion object {
		const val PRIMARY_KEY = "id"
	}
}
