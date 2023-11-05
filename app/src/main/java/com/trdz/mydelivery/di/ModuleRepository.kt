package com.trdz.mydelivery.di

import com.trdz.mydelivery.model.ADataSource
import com.trdz.mydelivery.model.InternalSource
import com.trdz.mydelivery.model.Repository
import com.trdz.mydelivery.model.RepositoryExecutor
import com.trdz.mydelivery.model.source_room.InternalStorage
import com.trdz.mydelivery.model.source_server.ServerRetrofit
import org.koin.dsl.module

val moduleRepository = module {
	single<ADataSource> { ServerRetrofit() }
	single<InternalSource> { InternalStorage() }
	single<Repository>() { RepositoryExecutor( internalSource = get(), externalSource = get() ) }
}


