package com.trdz.mydelivery.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.trdz.mydelivery.R
import com.trdz.mydelivery.core.CustomOnBackPressed
import com.trdz.mydelivery.core.Navigation
import com.trdz.mydelivery.utility.KEY_OPTIONS
import com.trdz.mydelivery.utility.KEY_THEME
import com.trdz.mydelivery.utility.stopToast
import org.koin.android.ext.android.inject

class MainActivity: AppCompatActivity() {

	//region Injected
	private val navigation: Navigation by inject()

	//endregion

	//region Customization
	override fun onBackPressed() {
		val fragmentList = supportFragmentManager.fragments

		var handled = false
		for (f in fragmentList) {
			if (f is CustomOnBackPressed) {
				handled = f.onBackPressed()
				if (handled) {
					break
				}
			}
		}

		if (!handled) super.onBackPressed()
	}

	//endregion

	//region Base realization
	override fun onDestroy() {
		stopToast()
		super.onDestroy()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		themeSettings()
		setContentView(R.layout.activity_main)
		if (savedInstanceState == null) {
			Log.d("@@@", "Start program")
			navigation.add(supportFragmentManager, WindowStart(), false, R.id.container_fragment_primal)
		}
	}

	private fun themeSettings() {
		when (getSharedPreferences(KEY_OPTIONS, Context.MODE_PRIVATE).getInt(KEY_THEME, 0)) {
			0 -> setTheme(R.style.MyBaseTheme)
			1 -> setTheme(R.style.MyGoldTheme)
			2 -> setTheme(R.style.MyFiolTheme)
		}
	}

	//endregion

}