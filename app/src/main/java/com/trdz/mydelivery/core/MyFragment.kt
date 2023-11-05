package com.trdz.mydelivery.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.trdz.mydelivery.presentation.showcase.view_model.StatusMessage

abstract class MyFragment<B : ViewBinding>(
	private val inflateBinding: (inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean) -> B,
): Fragment() {

	private var _binding: B? = null
	protected val binding get() = _binding!!

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
		initClean()
	}

	/**
	 * Специальное действие в момент очистки фрагмента
	 */
	open fun initClean() {}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initArgument()
	}

	/**
	 * Реакиця на получиные данные при создании фрагмента
	 */
	open fun initArgument() {}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = inflateBinding.invoke(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		prepInitialize()
		initialize()
		postInitialize()
	}

	/**
	 * Работа с фрагментов до определения вию модели а следовательно до ее атач тригера
	 */
	open fun prepInitialize() {
		setViewModel()
	}

	/**
	 * Привязка вью модели к фрагменту. Ничего если в у фрагмента ее нет
	 */
	protected abstract fun setViewModel()

	/**
	 * Работа с фрагментом до инициализации состовляющих его вью (кнопок, скролеров и т.п)
	 */
	open fun initialize() {
		binds()
	}

	/**
	 * Инициализация элементов вью
	 */
	protected abstract fun binds()

	/**
	 * Завершающие действия фрагмента, когда все его элементы ужа активны
	 */
	open fun postInitialize() {
		setViewModel()
	}

	/**
	 * Реакция фрагмента на присланые данные от вью модели
	 */
	open fun renderData(material: StatusMessage) {}

}