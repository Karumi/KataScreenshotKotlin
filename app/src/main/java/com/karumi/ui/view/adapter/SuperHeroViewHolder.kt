package com.karumi.ui.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.karumi.R
import com.karumi.ui.domain.model.SuperHero
import com.karumi.ui.presenter.SuperHeroesPresenter
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(itemView: View,
    private val presenter: SuperHeroesPresenter) : RecyclerView.ViewHolder(itemView) {
    private val photoImageView: ImageView = itemView.findViewById(R.id.iv_super_hero_photo)
    private val nameTextView: TextView = itemView.findViewById(R.id.tv_super_hero_name)
    private val avengersBadgeView: View = itemView.findViewById(R.id.iv_avengers_badge)

    private val context: Context
        get() = itemView.context

    fun render(superHero: SuperHero) {
        hookListeners(superHero)
        renderSuperHeroPhoto(superHero.photo)
        renderSuperHeroName(superHero.name)
        renderAvengersBadge(superHero.isAvenger)
    }

    private fun hookListeners(superHero: SuperHero) {
        itemView.setOnClickListener { presenter.onSuperHeroClicked(superHero) }
    }

    private fun renderSuperHeroPhoto(photo: String) {
        Picasso.with(context).load(photo).fit().centerCrop().into(photoImageView)
    }

    private fun renderSuperHeroName(name: String) {
        nameTextView.text = name
    }

    private fun renderAvengersBadge(isAvenger: Boolean) {
        avengersBadgeView.visibility = if (isAvenger) View.VISIBLE else View.GONE
    }
}