package com.trdz.mydelivery.model

import android.util.Log
import com.trdz.mydelivery.data.*
import com.trdz.mydelivery.model.source_server.ServerRetrofit
import com.trdz.mydelivery.utility.TYPE_NONE

class RepositoryExecutor(private val internalSource: InternalSource, private val  externalSource: ADataSource): Repository {

	private lateinit var aDataSource: ADataSource

	private var category: List<DataCategories> = listOf()
	private lateinit var filter: String

	private fun getDataSource(forcedInternalMode: Boolean) {
		if (forcedInternalMode) { aDataSource = internalSource as ADataSource }
		else { aDataSource = externalSource }
	}

	/** Отправка запроса на список категорий */
	override suspend fun connectionCategory(forcedInternalMode: Boolean): RequestCategoryResult {
		getDataSource(forcedInternalMode)
		Log.d("@@@", "Rep - start connection")
		return aDataSource.loadCategory()
	}

	/** Отправка запроса на список категорий */
	override suspend fun connectionBanner(): RequestBannerResult {
		Log.d("@@@", "Rep - start fake connection")
		return RequestBannerResult(200,listOf(DataBanner(0), DataBanner(1), DataBanner(2), DataBanner(3), DataBanner(4), DataBanner(5, TYPE_NONE)))
	}

	/** Отправка запроса на список категорий */
	override suspend fun connectionList(forcedInternalMode: Boolean): RequestListResult {
		getDataSource(forcedInternalMode)
		Log.d("@@@", "Rep - start connection")
		return aDataSource.loadList(filter)
	}

	/** Запоминание текущего вида листа категорий у пользователя */
	override fun approved(data: List<DataCategories>) {
		category = data
		filter = data[0].category
		internalSource.saveCategory(category)
	}

	override fun saveMealList(data: List<DataMeal>) {
		internalSource.saveList(data,filter)
	}

	override fun activateFilter(id: String, name: String): List<DataCategories> {
		category.forEach { data -> data.state = (data.id==id) }
		filter = name
		return category
	}

}
