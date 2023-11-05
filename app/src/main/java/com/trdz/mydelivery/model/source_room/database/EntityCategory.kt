package com.trdz.mydelivery.model.source_room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class EntityCategory(
	@PrimaryKey()
	@ColumnInfo(name = PRIMARY_KEY)
	val id: String,
	val category: String,
) {
	companion object {
		const val PRIMARY_KEY = "id"
	}
}
