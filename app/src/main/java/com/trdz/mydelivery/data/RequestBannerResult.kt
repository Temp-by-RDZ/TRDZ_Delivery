package com.trdz.mydelivery.data

/** Ответ на Запрос */
data class RequestBannerResult(
	val code: Int, 						//Код ответа на запрос
	val data: List<DataBanner>,
	val troubles: Throwable? = null
)
