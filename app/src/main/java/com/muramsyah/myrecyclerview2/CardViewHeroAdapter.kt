package com.muramsyah.myrecyclerview2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muramsyah.myrecyclerview2.databinding.ItemCardviewHeroBinding

class CardViewHeroAdapter(private val listHero: ArrayList<Hero>) :
    RecyclerView.Adapter<CardViewHeroAdapter.CardViewViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallBack

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view =
            ItemCardviewHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

    override fun getItemCount(): Int = listHero.size

    inner class CardViewViewHolder(private val binding: ItemCardviewHeroBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(hero.photo)
                    .apply(RequestOptions().override(350, 550))
                    .into(imgItemPhoto)

                tvItemName.text = hero.name
                tvItemDescription.text = hero.description

                btnSetFavorite.setOnClickListener { Toast.makeText(itemView.context, "Favorite ${hero.name}", Toast.LENGTH_SHORT).show() }
                btnSetShare.setOnClickListener { Toast.makeText(itemView.context, "Share ${hero.name}", Toast.LENGTH_SHORT).show() }

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(hero) }
            }
        }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: Hero)
    }
}