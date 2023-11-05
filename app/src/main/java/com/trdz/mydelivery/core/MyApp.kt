package com.trdz.mydelivery.core

import android.app.Application
import com.trdz.mydelivery.di.moduleData
import com.trdz.mydelivery.di.moduleMain
import com.trdz.mydelivery.di.moduleRepository
import com.trdz.mydelivery.di.moduleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@MyApp)
			modules(listOf(moduleMain, moduleRepository, moduleViewModel, moduleData))
		}
	}
}