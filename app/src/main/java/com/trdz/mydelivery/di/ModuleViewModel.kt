package com.trdz.mydelivery.di

import com.trdz.mydelivery.core.SingleLiveData
import com.trdz.mydelivery.presentation.showcase.view_model.StatusMessage
import com.trdz.mydelivery.presentation.showcase.view_model.ViewModelFactories
import org.koin.dsl.module

val moduleViewModel = module {
	factory<SingleLiveData<StatusMessage>>() { SingleLiveData() }
	single<ViewModelFactories>() {
		ViewModelFactories(
			dataLive = get(),
			repository = get(),
		)
	}
}


