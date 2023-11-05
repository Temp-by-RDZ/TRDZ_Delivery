package com.trdz.mydelivery.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.trdz.mydelivery.model.source_room.InternalStorage
import com.trdz.mydelivery.model.source_room.database.DataBase
import com.trdz.mydelivery.model.source_server.ServerRetrofitApi
import com.trdz.mydelivery.utility.DOMAIN
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val moduleData = module {
	single<ServerRetrofitApi>() {
		Retrofit.Builder().apply {
			baseUrl(DOMAIN)
			addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
		}.build().create(ServerRetrofitApi::class.java)
	}
	scope<InternalStorage> {
		scoped {
			Room.databaseBuilder(androidApplication(), DataBase::class.java, "test").build().orderDao()
		}
	}

}


