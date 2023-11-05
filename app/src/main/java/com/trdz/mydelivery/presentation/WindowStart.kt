package com.trdz.mydelivery.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.fragment.app.Fragment
import com.trdz.mydelivery.R
import com.trdz.mydelivery.core.Navigation
import com.trdz.mydelivery.databinding.FragmentWindowStartBinding
import com.trdz.mydelivery.utility.EFFECT_RISE
import com.trdz.mydelivery.presentation.showcase.view.WindowMain
import org.koin.android.ext.android.inject

/** Заставка перед стартом приложения */
class WindowStart: Fragment() {

	//region Injected
	private val navigation: Navigation by inject()

	//endregion

	//region Elements
	private var _binding: FragmentWindowStartBinding? = null
	private val binding get() = _binding!!

	//endregion

	//region Base realization
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentWindowStartBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initialize()
	}

	//endregion

	//region Main functional
	/** Задание начального исполнения основного функционала*/
	private fun initialize() {
		runAnimation()
		createMainWindow()
	}

	/** Проигрывание анимаүии при старте приложения */
	private fun runAnimation() {
		binding.firstView.animate().apply {
			alpha(0.0f)
			duration = 2900L
			withEndAction {
				if (this@WindowStart.isAdded) {
					requireActivity().supportFragmentManager.beginTransaction().detach(this@WindowStart).commit()
				}
			}
			start()
		}
	}

	/** Подготовка первого экрана приложения */
	private fun createMainWindow() {
		Handler(Looper.getMainLooper()).postDelayed({
			navigation.replace(requireActivity().supportFragmentManager, WindowMain.newInstance(true), false, container = R.id.container_fragment_navigation , effect = EFFECT_RISE)
		}, 100L)
	}

	//endregion

	companion object {
		fun newInstance() = WindowStart()
	}

}

