package com.trdz.mydelivery.presentation.showcase.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trdz.mydelivery.core.SingleLiveData
import com.trdz.mydelivery.model.Repository

class ViewModelFactories(
	private val dataLive: SingleLiveData<StatusMessage>,
	private val repository: Repository,
): ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T: ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
		MainViewModel::class.java -> MainViewModel(dataLive,repository)
		else -> throw IllegalArgumentException("Unknown ViewModel class")
	} as T

}