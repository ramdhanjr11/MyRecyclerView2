package com.muramsyah.myrecyclerview2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muramsyah.myrecyclerview2.databinding.ItemRowHeroBinding

class ListHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallBack

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowHeroBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

    override fun getItemCount(): Int = listHero.size

    inner class ListViewHolder(private val binding: ItemRowHeroBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            with(binding){
                Glide.with(itemView.context)
                    .load(hero.photo)
                    .apply(RequestOptions().override(55, 55))
                    .into(imgItemPhoto)
                tvItemName.text = hero.name
                tvItemDescription.text = hero.description

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(hero) }
            }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: Hero)
    }
}