package com.example.gulp.iu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gulp.R
import com.example.gulp.base.BaseViewHolder
import com.example.gulp.data.model.Drink
import kotlinx.android.synthetic.main.drink_row.view.*

class MainAdapter(private val context: Context, private val drinkList: List<Drink>,
                  private val itemClickLister:OnDrinkClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.drink_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(drinkList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(itemView.img_drink)
            itemView.txt_title.text = item.number
            itemView.txt_description.text = item.description
            itemView.setOnClickListener { itemClickLister.onDrinkClick(item) }
        }
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    interface OnDrinkClickListener{
        fun onDrinkClick(drink: Drink)
    }
}