package com.trdz.mydelivery.presentation.showcase.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trdz.mydelivery.databinding.ElementBannerBinding
import com.trdz.mydelivery.databinding.ElementHiderBinding
import com.trdz.mydelivery.data.DataBanner
import com.trdz.mydelivery.utility.TYPE_HEAD

class WindowShowcaseBannerRecycle(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private var list: List<DataBanner> = emptyList()

	@SuppressLint("NotifyDataSetChanged")
	fun setList(newList: List<DataBanner>) {
		this.list = newList
		notifyDataSetChanged()
	}

	override fun getItemViewType(position: Int): Int {
		return list[position].type
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return when (viewType) {
			TYPE_HEAD -> {
				val view = ElementBannerBinding.inflate(LayoutInflater.from(parent.context))
				Element(view.root)
			}
			else -> {
				val view = ElementHiderBinding.inflate(LayoutInflater.from(parent.context))
				ElementNone(view.root)
			}
		}

	}

	override fun onBindViewHolder(
		holder: RecyclerView.ViewHolder,
		position: Int,
		payloads: MutableList<Any>,
	) {
		if (payloads.isEmpty()) {
			super.onBindViewHolder(holder, position, payloads)
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		(holder as ListElement).myBind(list[position])
	}

	override fun getItemCount(): Int {
		return list.size
	}

	abstract inner class ListElement(view: View): RecyclerView.ViewHolder(view) {
		abstract fun myBind(data: DataBanner)
	}

	inner class Element(view: View): ListElement(view) {

		override fun myBind(data: DataBanner) {
			(ElementBannerBinding.bind(itemView)).apply {
				root.setOnClickListener {}
			}
		}
	}

	inner class ElementNone(view: View): ListElement(view) {
		override fun myBind(data: DataBanner) {
		}
	}
}