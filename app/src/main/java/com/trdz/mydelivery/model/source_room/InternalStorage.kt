package com.trdz.mydelivery.model.source_room

import android.util.Log
import com.trdz.mydelivery.data.DataCategories
import com.trdz.mydelivery.data.DataMeal
import com.trdz.mydelivery.data.RequestCategoryResult
import com.trdz.mydelivery.data.RequestListResult
import com.trdz.mydelivery.model.ADataSource
import com.trdz.mydelivery.model.InternalSource
import com.trdz.mydelivery.model.source_room.database.EntityCategory
import com.trdz.mydelivery.model.source_room.database.EntityMeals
import com.trdz.mydelivery.model.source_room.database.OrderDao
import com.trdz.mydelivery.utility.TYPE_NONE
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class InternalStorage: ADataSource, InternalSource, KoinScopeComponent {

	//region Injected

	override val scope: Scope by getOrCreateScope()
	private val dataDao: OrderDao by inject()
	private fun getData(): OrderDao = dataDao

	//endregion

	//region Category

	override fun saveCategory(list: List<DataCategories>) {
		Log.d("@@@", "Rep - Start save process for categories")
		try {
			val categoryList: MutableList<EntityCategory> = emptyList<EntityCategory>().toMutableList()
			list.forEach { elem ->
				categoryList.add(ResponseMapper.toStorage(elem))
			}
			getData().saveCategory(categoryList.toList())
			Log.d("@@@", "Rep - ...Done")
		}
		catch (e: Exception) {
			Log.d("@@@", "Rep - ...Failed ${e.message}")
		}
	}

	override suspend fun loadCategory(): RequestCategoryResult {
		return try {
			val response = getData().getCategory()
			if (response.isEmpty()) RequestCategoryResult(404,listOf<DataCategories>(),responseEmpty())
			else responseFormation(response)
		}
		catch (error: Exception) {
			RequestCategoryResult(-2,listOf<DataCategories>(),responseFail(error))
		}
	}

	private fun responseFormation(response: List<EntityCategory>): RequestCategoryResult {
		val categoryList: MutableList<DataCategories> = emptyList<DataCategories>().toMutableList()
		response.forEach { entity ->
			categoryList.add(ResponseMapper.fromStorage(entity)
			)
		}
		categoryList.first().state = true		//Выделяем одну категорию по умолчанию
		categoryList.last().type = TYPE_NONE 	//Закрываем список конечным элементов
		return RequestCategoryResult(200, categoryList)
	}

	//endregion

	//region Data Favor

	override fun saveList(list: List<DataMeal>, filter: String) {
		Log.d("@@@", "Rep - Start save process for meals")
		try {
			val mealList: MutableList<EntityMeals> = emptyList<EntityMeals>().toMutableList()
			list.forEach { elem ->
				mealList.add(ResponseMapper.toStorage(elem,filter))
			}
			getData().saveMeals(mealList.toList())
			Log.d("@@@", "Rep - ...Done")
		}
		catch (e: Exception) {
			Log.d("@@@", "Rep - ...Failed ${e.message}")
		}
	}

	override suspend fun loadList(filter: String): RequestListResult {
		return try {
			val response = getData().getMeals(filter)
			if (response.isEmpty()) RequestListResult(404,listOf<DataMeal>(),responseEmpty())
			else responseFormation(response)
		}
		catch (error: Exception) {
			RequestListResult(-2,listOf<DataMeal>(),responseFail(error))
		}
	}

	private fun responseFormation(response: List<EntityMeals>): RequestListResult {
		val mealList: MutableList<DataMeal> = emptyList<DataMeal>().toMutableList()
		response.forEach { entity ->
			mealList.add(ResponseMapper.fromStorage(entity)
			)
		}
		return RequestListResult(200,mealList)
	}

//endregion

	private fun responseEmpty() = Throwable("Error code: 404, Data lost\n")
	private fun responseFail(error: Exception) = Throwable("fError code: -1, Internal storage unavailable:\n$error")
}