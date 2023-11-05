package com.trdz.mydelivery.presentation.acount

import com.trdz.mydelivery.core.MyFragment
import com.trdz.mydelivery.databinding.FragmentWindowPlaceholderBinding

class WindowPlaceholder: MyFragment<FragmentWindowPlaceholderBinding>(FragmentWindowPlaceholderBinding::inflate)  {

	//region Main functional

	override fun setViewModel() {
		//none
	}
	override fun binds() = with(binding) {
		//none
	}

	//endregion


	companion object {
		fun newInstance() = WindowPlaceholder()
	}
}