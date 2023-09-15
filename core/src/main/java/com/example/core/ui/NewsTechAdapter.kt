package com.example.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.NewsItemListBinding
import com.example.core.domain.model.NewsTech

class NewsTechAdapter: RecyclerView.Adapter<NewsTechAdapter.NewsTechViewHolder>() {

    private var listData = ArrayList<NewsTech>()
    var onItemClick: ((NewsTech) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<NewsTech>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = NewsTechViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_item_list, parent, false))

    override fun onBindViewHolder(holder: NewsTechAdapter.NewsTechViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class NewsTechViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = NewsItemListBinding.bind(itemView)
        fun bind(data: NewsTech) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(imgNews)
                tvTitle.text = data.title
                tvDescription.text = data.headline
                tvDate.text = data.pusblisedAt

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}