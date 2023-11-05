package com.trdz.mydelivery.presentation.showcase.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trdz.mydelivery.data.DataCategories
import com.trdz.mydelivery.databinding.ElementAlterBinding
import com.trdz.mydelivery.databinding.ElementCategoryBinding
import com.trdz.mydelivery.databinding.ElementHiderBinding
import com.trdz.mydelivery.utility.TYPE_ALTER
import com.trdz.mydelivery.utility.TYPE_HEAD

class WindowShowcaseFilterRecycle(private val clickerExecutor: WindowShowcaseOnFilterClick): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private var list: List<DataCategories> = emptyList()

	@SuppressLint("NotifyDataSetChanged")
	fun setList(newList: List<DataCategories>) {
		this.list = newList
		notifyDataSetChanged()
	}

	override fun getItemViewType(position: Int): Int {
		return if (list[position].state) { TYPE_ALTER }
		else { list[position].type }
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return when (viewType) {
			TYPE_HEAD -> {
				val view = ElementCategoryBinding.inflate(LayoutInflater.from(parent.context))
				Element(view.root)
			}
			TYPE_ALTER -> {
				val view = ElementAlterBinding.inflate(LayoutInflater.from(parent.context))
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
		abstract fun myBind(data: DataCategories)
	}

	inner class Element(view: View): ListElement(view) {

		override fun myBind(data: DataCategories) {
			(ElementCategoryBinding.bind(itemView)).apply {
				root.setOnClickListener {
					if (!data.state) {clickerExecutor.onItemClick(data.id,data.category) }
				}
				category.text = data.category
			}
		}
	}

	inner class ElementNone(view: View): ListElement(view) {
		override fun myBind(data: DataCategories) {
		}
	}
}