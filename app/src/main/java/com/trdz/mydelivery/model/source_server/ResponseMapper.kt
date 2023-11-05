package com.trdz.mydelivery.model.source_server

import com.trdz.mydelivery.data.DataCategories
import com.trdz.mydelivery.data.DataMeal
import com.trdz.mydelivery.model.source_server.dto_category.DTOCategory
import com.trdz.mydelivery.model.source_server.dto_list.DTOMeal
import com.trdz.mydelivery.utility.TYPE_HEAD

object ResponseMapper {

	fun mapToEntity(dto: DTOCategory): DataCategories {
		return with(dto) {
			DataCategories(
				id = idCategory,
				category = strCategory,
				type = TYPE_HEAD,
			)
		}
	}

	fun mapToEntity(dto: DTOMeal): DataMeal {
		return with(dto) {
			DataMeal(
				id = idMeal,
				name = strMeal,
				pic = strMealThumb,
			)
		}
	}
}
