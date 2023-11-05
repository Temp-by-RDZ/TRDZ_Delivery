package com.trdz.mydelivery.presentation.showcase.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import com.trdz.mydelivery.data.DataMeal
import com.trdz.mydelivery.databinding.ElementBannerBinding
import com.trdz.mydelivery.databinding.ElementFoodBinding
import com.trdz.mydelivery.utility.loadSvg
import kotlinx.android.synthetic.main.element_food.view.*

class WindowShowcaseFoodRecycle(): RecyclerView.Adapter<WindowShowcaseFoodRecycle.Element>() {

	private var list: List<DataMeal> = emptyList()

	@SuppressLint("NotifyDataSetChanged")
	fun setList(newList: List<DataMeal>) {
		this.list = newList
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Element {

		val view = ElementFoodBinding.inflate(LayoutInflater.from(parent.context),parent,false)
		return Element(view.root)
		//return Element(ElementFoodBinding.inflate(LayoutInflater.from(parent.context)).root)
	}

	override fun onBindViewHolder(holder: Element, position: Int) {
		holder.myBind(list[position])
	}

	override fun getItemCount(): Int {
		return list.size
	}

	inner class Element(view: View): RecyclerView.ViewHolder(view) {

		fun myBind(data: DataMeal) {
			(ElementFoodBinding.bind(itemView)).apply {
				description.title.text = data.name
				picture.load(data.pic) {
					listener(
						onSuccess = { _, _ ->
							// do nothing
						},
						onError = { request: ImageRequest, throwable: Throwable ->
							Log.d("@@@", "App - coil error $throwable")
							picture.loadSvg(data.pic) //если вдруг coil помрет
						})
				}

			}
		}
	}

}