package com.trdz.mydelivery.model

import com.trdz.mydelivery.data.DataCategories
import com.trdz.mydelivery.data.DataMeal
import com.trdz.mydelivery.data.RequestCategoryResult
import com.trdz.mydelivery.data.RequestListResult

/** Интерфейс для внутренних хранилищ данных */
interface InternalSource {
	fun saveCategory(list: List<DataCategories>)
	fun saveList(list: List<DataMeal>, filter: String)
}