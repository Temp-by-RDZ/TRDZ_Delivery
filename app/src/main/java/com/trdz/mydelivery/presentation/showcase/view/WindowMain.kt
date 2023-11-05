package com.trdz.mydelivery.presentation.showcase.view

import android.os.Bundle
import com.trdz.mydelivery.R
import com.trdz.mydelivery.core.MyFragment
import com.trdz.mydelivery.core.Navigation
import com.trdz.mydelivery.databinding.FragmentNavigationBinding
import com.trdz.mydelivery.presentation.acount.WindowPlaceholder
import com.trdz.mydelivery.presentation.backet.WindowBasket
import com.trdz.mydelivery.utility.*
import org.koin.android.ext.android.inject

class WindowMain: MyFragment<FragmentNavigationBinding>(FragmentNavigationBinding::inflate) {

	//region Injected

	private val navigation: Navigation by inject()

	//endregion

	//region Elements

	var isFirst = false

	//endregion

	//region Main functional

	override fun initArgument() {
		arguments?.let {
			isFirst = it.getBoolean(KEY_FINSTANCE)
		}
	}

	override fun setViewModel() {
		//none
	}

	override fun binds() = with(binding) {
		bottomNavigation.setOnItemSelectedListener { item ->
			if (!item.isChecked) {
				when (item.itemId) {
					R.id.action_bottom_navigation_order -> {
						navigation.replace(requireActivity().supportFragmentManager, WindowShowcase(), false, R.id.container_sub_fragment,getEffect())
					}
					R.id.action_bottom_navigation_acount -> {
						navigation.replace(requireActivity().supportFragmentManager, WindowPlaceholder(), false, R.id.container_sub_fragment,getEffect())
					}
					R.id.action_bottom_navigation_basket -> {
						navigation.replace(requireActivity().supportFragmentManager, WindowBasket(), false, R.id.container_sub_fragment, EFFECT_MOVEL)
					}
				}
			}
			true
		}
	}

	private fun getEffect(): String {
		return when (binding.bottomNavigation.getSelectedItem()) {
			R.id.action_bottom_navigation_order -> EFFECT_MOVEL
			R.id.action_base -> EFFECT_MOVEU
			else -> EFFECT_MOVER
		}
	}

	override fun postInitialize() {
		binding.bottomNavigation.selectedItemId = R.id.action_bottom_navigation_order
	}

	//endregion

	companion object {
		@JvmStatic
		fun newInstance(first_instance: Boolean) =
			WindowMain().apply {
				arguments = Bundle().apply {
					putBoolean(KEY_FINSTANCE, first_instance)
				}
			}
	}

}