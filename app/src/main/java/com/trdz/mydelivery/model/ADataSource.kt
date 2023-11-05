package com.trdz.mydelivery.model

import com.trdz.mydelivery.data.RequestCategoryResult
import com.trdz.mydelivery.data.RequestListResult

/** Интерфейс для Источников Данных */
interface ADataSource {
	suspend fun loadCategory(): RequestCategoryResult
	suspend fun loadList(filter: String): RequestListResult
}