package com.android.doglistexample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.doglistexample.R
import com.android.doglistexample.data.model.DogModel
import com.android.doglistexample.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogListAdapter(private val images: List<String>) : RecyclerView.Adapter<DogListAdapter.DogHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun onBindViewHolder(holder: DogHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    class DogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemDogBinding.bind(itemView)

        fun bind(image: String){
            Picasso.get().load(image).into(binding.ivDog)
        }
    }

}

