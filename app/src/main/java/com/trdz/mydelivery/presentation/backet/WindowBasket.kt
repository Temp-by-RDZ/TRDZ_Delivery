package com.trdz.mydelivery.presentation.backet

import com.trdz.mydelivery.core.MyFragment
import com.trdz.mydelivery.databinding.FragmentWindowPlaceholderBinding

class WindowBasket: MyFragment<FragmentWindowPlaceholderBinding>(FragmentWindowPlaceholderBinding::inflate)  {

	//region Main functional

	override fun setViewModel() {
		//none
	}
	override fun binds() = with(binding) {
		//none
	}

	//endregion


	companion object {
		fun newInstance() = WindowBasket()
	}
}