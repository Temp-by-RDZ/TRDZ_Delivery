package com.trdz.mydelivery.data

/** Ответ на Запрос */
data class RequestListResult(
	val code: Int, 						//Код ответа на запрос
	val data: List<DataMeal>,
	val troubles: Throwable? = null
)
