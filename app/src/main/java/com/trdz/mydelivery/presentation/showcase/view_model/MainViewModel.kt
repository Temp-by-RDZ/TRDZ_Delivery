package com.trdz.mydelivery.presentation.showcase.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.trdz.mydelivery.core.SingleLiveData
import com.trdz.mydelivery.data.RequestBannerResult
import com.trdz.mydelivery.model.Repository
import com.trdz.mydelivery.data.RequestCategoryResult
import com.trdz.mydelivery.data.RequestListResult
import kotlinx.coroutines.*

/** Главная VM */
class MainViewModel(
	private val dataLive: SingleLiveData<StatusMessage>,
	private val repository: Repository,
): ViewModel(), ServerResponse {

	private val coroutineScope = CoroutineScope(
		Dispatchers.IO
				+ SupervisorJob()
				+ CoroutineExceptionHandler { _, throwable ->
			handleError(throwable)
		})

	private fun handleError(throwable: Throwable) {
		Log.w("@@@", "Prs - Coroutine dead $throwable")
	}

	private var jobs: Job? = null

	fun getData(): LiveData<StatusMessage> = dataLive

	/** Подготовка запроса*/
	fun initialize() {
		requestBanner()
		requestCategory()
	}

	fun activateFilter(id: String, name: String) {
		successCategory(RequestCategoryResult(0,repository.activateFilter(id,name)))
	}

	fun getMealList() {
		requestList()
	}

	private fun requestBanner() {
		Log.d("@@@", "Prs - Start loading")
		jobs = coroutineScope.launch {
			val response = repository.connectionBanner()
			if (response.code in 200..299) {
				Log.d("@@@", "Prs - External load for Banner is complete")
				successBanner(response)
			}
			else {
				Log.w("@@@", "Prs - Failed external load")
				fail(response.code, response.troubles)
			}
		}
	}

	/** Выполнение запроса на список категорий */
	private fun requestCategory() {
		Log.d("@@@", "Prs - Start external load of categories")
		jobs = coroutineScope.launch {
			var response = repository.connectionCategory()
			if (response.code in 200..299) {
				repository.approved(response.data)
				Log.d("@@@", "Prs - External load for Categories is complete")
				successCategory(response)
			}
			else {
				Log.w("@@@", "Prs - Failed external load")
				fail(response.code, response.troubles)
				Log.d("@@@", "Prs - Start internal load")
				response = repository.connectionCategory(true)
				if (response.code in 200..299) {
					repository.approved(response.data)
					Log.d("@@@", "Prs - Internal load for Categories is complete")
					successCategory(response)
				}
				else {
					Log.w("@@@", "Prs - Failed external load")
					fail(response.code, response.troubles)
				}
			}
		}
	}

	private fun requestList() {
		Log.d("@@@", "Prs - Start external load of meals")
		jobs = coroutineScope.launch {
			dataLive.postValue(StatusMessage.Load)
			var response = repository.connectionList()
			if (response.code in 200..299) {
				repository.saveMealList(response.data)
				Log.d("@@@", "Prs - External load for Meals is complete")
				successList(response)
			}
			else {
				Log.w("@@@", "Prs - Failed external load")
				fail(response.code, response.troubles)
				Log.d("@@@", "Prs - Start internal load")
				response = repository.connectionList(true)
				if (response.code in 200..299) {
					repository.saveMealList(response.data)
					Log.d("@@@", "Prs - Internal load for Meals is complete")
					successList(response)
				}
				else {
					Log.w("@@@", "Prs - Failed external load")
					fail(response.code, response.troubles)
				}
			}
		}
	}

	/** Реакция MV на успех запроса */
	override fun successCategory(data: RequestCategoryResult) {
		Log.d("@@@", "Mod - get success answer for Category Request")
		dataLive.postValue(StatusMessage.SuccessCategory(data))
	}
	/** Реакция MV на успех запроса */
	override fun successBanner(data: RequestBannerResult) {
		Log.d("@@@", "Mod - get success answer for Banners Request")
		dataLive.postValue(StatusMessage.SuccessBanner(data))
	}	/** Реакция MV на успех запроса */

	override fun successList(data: RequestListResult) {
		Log.d("@@@", "Mod - get success answer for Meals Request")
		dataLive.postValue(StatusMessage.SuccessList(data))
	}

	/** Реакция MV на ошибку запроса */
	override fun fail(code: Int, throwable: Throwable?) {
		Log.d("@@@", "Mod - request failed $code by $throwable")
		val message = throwable ?: Throwable("Unspecified Error")
		dataLive.postValue(StatusMessage.Error(code, message))
	}
}