package com.muramsyah.myrecyclerview2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muramsyah.myrecyclerview2.databinding.ItemGridHeroBinding

class GridHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallBack

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = ItemGridHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridHeroAdapter.GridViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

    override fun getItemCount(): Int = listHero.size

    inner class GridViewHolder(private val binding: ItemGridHeroBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(hero.photo)
                    .apply(RequestOptions().override(350, 550))
                    .into(imgItemPhoto)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(hero) }
            }
        }

    }

    interface OnItemClickCallBack {
        fun onItemClicked(data : Hero)
    }
}