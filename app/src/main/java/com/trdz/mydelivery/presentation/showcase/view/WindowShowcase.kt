package com.trdz.mydelivery.presentation.showcase.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.trdz.mydelivery.R
import com.trdz.mydelivery.core.MyFragment
import com.trdz.mydelivery.databinding.FragmentWindowShowcaseBinding
import com.trdz.mydelivery.utility.*
import com.trdz.mydelivery.presentation.showcase.view_model.MainViewModel
import com.trdz.mydelivery.presentation.showcase.view_model.StatusMessage
import com.trdz.mydelivery.presentation.showcase.view_model.ViewModelFactories
import org.koin.android.ext.android.inject

class WindowShowcase: MyFragment<FragmentWindowShowcaseBinding>(FragmentWindowShowcaseBinding::inflate),
	WindowShowcaseOnFilterClick {

	//region Injected
	private val factory: ViewModelFactories by inject()
	private val viewModel: MainViewModel by viewModels {
		factory
	}

	//endregion

	//region Elements
	private val bannerAdapter = WindowShowcaseBannerRecycle()
	private val filterAdapter = WindowShowcaseFilterRecycle(this)
	private val mealListAdapter = WindowShowcaseFoodRecycle()
	var isFirst = false

	//endregion

	//region Main functional

	override fun initArgument() {
		arguments?.let {
			isFirst = it.getBoolean(KEY_FINSTANCE)
		}
	}

	override fun setViewModel() {
		val observer = Observer<StatusMessage> { renderData(it) }
		viewModel.getData().observe(viewLifecycleOwner, observer)
	}

	override fun initialize() {
		super.initialize()
		viewModel.initialize()
	}

	override fun binds() = with(binding) {
		headline.adapter = bannerAdapter
		categories.adapter = filterAdapter
		mealList.adapter = mealListAdapter
	}

	override fun renderData(material: StatusMessage) {
		when (material) {
			StatusMessage.Load -> {
				binding.loadingLayout.show()
			}
			is StatusMessage.Error -> {
				when (material.code) {
					404 -> showToast(requireContext(),getString(R.string.toast_for_404_error))
					-1 -> showToast(requireContext(),getString(R.string.toast_for_m1_error))
					-2 -> showToast(requireContext(),getString(R.string.toast_for_m2_error))
				}
			}
			is StatusMessage.SuccessCategory -> {
				filterAdapter.setList(material.data.data)
				viewModel.getMealList()
			}
			is StatusMessage.SuccessBanner -> {
				bannerAdapter.setList(material.data.data)
			}
			is StatusMessage.SuccessList -> {
				binding.loadingLayout.hide()
				mealListAdapter.setList(material.data.data)
			}
		}
	}

	//endregion

	//region Adapters controller

	override fun onItemClick(id: String, name: String) {
		viewModel.activateFilter(id,name)
	}

	//endregion

	companion object {
		@JvmStatic
		fun newInstance(first_instance: Boolean) =
			WindowShowcase().apply {
				arguments = Bundle().apply {
					putBoolean(KEY_FINSTANCE, first_instance)
				}
			}
	}

}