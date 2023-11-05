package com.trdz.mydelivery.data

/** Ответ на Запрос */
data class RequestCategoryResult(
	val code: Int, 						//Код ответа на запрос
	val data: List<DataCategories>,
	val troubles: Throwable? = null
)
