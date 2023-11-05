package com.trdz.mydelivery.model.source_server

import com.trdz.mydelivery.model.source_server.dto_category.DTOCategories
import com.trdz.mydelivery.model.source_server.dto_list.DTOList
import com.trdz.mydelivery.utility.PATH_CATEGORY
import com.trdz.mydelivery.utility.PATH_FILTER
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerRetrofitApi {
	@GET(PATH_CATEGORY)
	fun getResponseCategory(): Call<DTOCategories>

	@GET(PATH_FILTER)
	fun getResponse(@Query("c") search: String,): Call<DTOList>
}


