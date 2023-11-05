package com.trdz.mydelivery.model.source_room.database

import androidx.room.*

@Dao
interface OrderDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun saveCategory(category: List<EntityCategory>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun saveMeals(meals: List<EntityMeals>)

	@Transaction
	@Query("SELECT * FROM meals WHERE category = :filter")
	abstract fun getMeals(filter: String): List<EntityMeals>

	@Transaction
	@Query("SELECT * FROM category")
	abstract fun getCategory(): List<EntityCategory>

}