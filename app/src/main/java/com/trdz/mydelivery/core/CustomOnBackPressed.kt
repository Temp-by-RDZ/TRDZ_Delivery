package com.trdz.mydelivery.core

/** Интерфейс для  фрагментов которым требуется нестандартная рреакция на кнопку "назад" */
interface CustomOnBackPressed {
	fun onBackPressed(): Boolean
}