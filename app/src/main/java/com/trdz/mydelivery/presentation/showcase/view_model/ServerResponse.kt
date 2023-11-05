package com.trdz.mydelivery.presentation.showcase.view_model

import com.trdz.mydelivery.data.RequestBannerResult
import com.trdz.mydelivery.data.RequestCategoryResult
import com.trdz.mydelivery.data.RequestListResult

/** Ожидаемые действия VM на обращения */
interface ServerResponse {
	fun successCategory(data: RequestCategoryResult)
	fun successBanner(data: RequestBannerResult)
	fun successList(data: RequestListResult)
	fun fail(code: Int, throwable: Throwable?)
}