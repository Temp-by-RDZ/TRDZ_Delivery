package com.trdz.mydelivery.di

import com.trdz.mydelivery.R
import com.trdz.mydelivery.core.Navigation
import org.koin.dsl.module

val moduleMain = module {
	single<Navigation>() { Navigation(R.id.container_fragment_base) }

}


