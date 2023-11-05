package com.trdz.mydelivery.presentation.showcase.view_model

import com.trdz.mydelivery.data.RequestBannerResult
import com.trdz.mydelivery.data.RequestCategoryResult
import com.trdz.mydelivery.data.RequestListResult

/** Ответы VM для фрагментов */
sealed class StatusMessage {
	object Load : StatusMessage()
	data class SuccessCategory(val data: RequestCategoryResult) : StatusMessage()
	data class SuccessBanner(val data: RequestBannerResult) : StatusMessage()
	data class SuccessList(val data: RequestListResult) : StatusMessage()
	data class Error(val code: Int, val error: Throwable) : StatusMessage()
}