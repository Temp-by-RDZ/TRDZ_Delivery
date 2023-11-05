package com.trdz.mydelivery.model.source_server

import com.trdz.mydelivery.data.DataCategories
import com.trdz.mydelivery.data.DataMeal
import com.trdz.mydelivery.model.ADataSource
import com.trdz.mydelivery.data.RequestCategoryResult
import com.trdz.mydelivery.data.RequestListResult
import com.trdz.mydelivery.model.source_server.dto_category.DTOCategories
import com.trdz.mydelivery.model.source_server.dto_list.DTOList
import com.trdz.mydelivery.utility.TYPE_NONE
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Response

class ServerRetrofit(): ADataSource {

	//region Injected

	private val data: ServerRetrofitApi by inject(ServerRetrofitApi::class.java)

	//endregion

	override suspend fun loadCategory(): RequestCategoryResult {
		return try { responseFormation(response = data.getResponseCategory().execute()) }
		catch (error: Exception) {
			RequestCategoryResult(-1, listOf(), responseFail(error))
		}
	}

	private fun responseFormation(response: Response<DTOCategories>): RequestCategoryResult {
		return if (response.isSuccessful) response.body()!!.run {
			val list: MutableList<DataCategories> = emptyList<DataCategories>().toMutableList()
			categories.forEach { category ->  list.add(ResponseMapper.mapToEntity(category)) }
			list.add(DataCategories("END Point", TYPE_NONE)) 			// Установка конца листа
			list[0].state = true 											// Включение выборки по умолчанию
			RequestCategoryResult(response.code(), list)
		}
		else RequestCategoryResult(response.code(), listOf())
	}

	override suspend fun loadList(filter: String): RequestListResult {
		return try { responseFormation(response = data.getResponse(filter).execute()) }
		catch (error: Exception) {
			RequestListResult(-1, listOf(), responseFail(error))
		}
	}

	private fun responseFormation(response: Response<DTOList>): RequestListResult {
		return if (response.isSuccessful) response.body()!!.run {
			val list: MutableList<DataMeal> = emptyList<DataMeal>().toMutableList()
			meals.forEach { meal ->  list.add(ResponseMapper.mapToEntity(meal)) }
			RequestListResult(response.code(), list)
		}
		else RequestListResult(response.code(), listOf())
	}

	private fun responseFail(error: Exception) = Throwable("Error code: -1, Internet connection lost or current data available:\n$error")
}
