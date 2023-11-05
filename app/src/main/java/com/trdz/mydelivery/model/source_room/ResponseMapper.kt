package com.trdz.mydelivery.model.source_room

import com.trdz.mydelivery.data.DataCategories
import com.trdz.mydelivery.data.DataMeal
import com.trdz.mydelivery.model.source_room.database.EntityCategory
import com.trdz.mydelivery.model.source_room.database.EntityMeals

object ResponseMapper {

	fun toStorage(data: DataMeal, filter: String): EntityMeals {
		return with(data) {
			EntityMeals(
				id = id,
				name = name,
				pic = pic,
				category = filter
			)
		}
	}

	fun fromStorage(entity: EntityMeals): DataMeal {
		return with(entity) {
			DataMeal(
				id = id,
				name = name,
				pic = pic,
			)
		}
	}

	fun toStorage(data: DataCategories): EntityCategory {
		return with(data) {
			EntityCategory(
				id = id,
				category = category,
			)
		}
	}

	fun fromStorage(entity: EntityCategory): DataCategories {
		return with(entity) {
			DataCategories(
				id = id,
				category = category,
			)
		}
	}

}
