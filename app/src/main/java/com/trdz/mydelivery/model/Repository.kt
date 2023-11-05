package com.trdz.mydelivery.model

import com.trdz.mydelivery.data.*

/** Интерфейс для основного репозитория */
interface Repository {
	suspend fun connectionCategory(forcedInternalMode: Boolean = false) : RequestCategoryResult
	suspend fun connectionBanner() : RequestBannerResult
	suspend fun connectionList(forcedInternalMode: Boolean = false) : RequestListResult
	fun activateFilter(id: String, name: String): List<DataCategories>
	fun approved(data: List<DataCategories>)
	fun saveMealList(data: List<DataMeal>)
}