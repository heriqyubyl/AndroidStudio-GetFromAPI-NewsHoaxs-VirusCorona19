package com.example.apikeynews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class MyAdapter (val context: Context, var dataList: ArrayList<CEOModel>?): RecyclerView.Adapter<MyAdapter.MyViewholder>() {
    class MyViewholder(view: View) : RecyclerView.ViewHolder(view) {
        val llMain = view.llMain
        val tvid = view.tvId
        val tvtitle = view.tvTitle
        val tvurl = view.tvUrl
        val tvtimestamp = view.tvTimeStamp
    }

    // method untuk membuat view holder
    // inflate = memunculkan data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        return MyViewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        val item = dataList?.get(position)

        holder.tvid.text = (position + 1).toString()
        holder.tvtitle.text = item?.title.toString()
        holder.tvurl.text = item?.url.toString()
        holder.tvtimestamp.text = item?.timestamp.toString()

        if (position % 2 == 0) {
            holder.llMain.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        } else {
            holder.llMain.setBackgroundColor(ContextCompat.getColor(context, R.color.nila))
        }
    }
}


