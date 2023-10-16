package com.example.fillinblank

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.fillinblank.ItemClass.Companion.LayoutOne
import com.example.fillinblank.ItemClass.Companion.LayoutTwo


class QuestionMakerAdapter(var itemClassList:ArrayList<ItemClass>,var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (itemClassList[position].viewType) {
            0 -> LayoutOne
            1 -> LayoutTwo
            else -> -1
        }
    }

    inner class LayoutOneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textview: TextView = itemView.findViewById(R.id.itemTxt)


        fun setView(text: String) {
            textview.text = text
        }
    }

    inner class LayoutTwoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val constraintLayout: LinearLayout = itemView.findViewById(R.id.itemContainer)


        fun setViews() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LayoutOne -> {
                val layoutOne = LayoutInflater.from(parent.context)
                    .inflate(R.layout.itemtextview, parent, false)
                LayoutOneViewHolder(layoutOne)
            }
            LayoutTwo -> {
                val layoutTwo = LayoutInflater.from(parent.context)
                    .inflate(R.layout.itemconteinerview, parent, false)
                LayoutTwoViewHolder(layoutTwo)
            }
            else -> error("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (itemClassList[position].viewType) {
            LayoutOne -> {
                val text = itemClassList[position].text
                (holder as LayoutOneViewHolder).setView(text!!)
            }
            LayoutTwo -> {
                (holder as LayoutTwoViewHolder).constraintLayout.setOnDragListener(MainActivity.MyDragListener(mContext,itemClassList[position]))
            }
            else -> return
        }
    }

    override fun getItemCount(): Int {
        return itemClassList.size
    }
}

