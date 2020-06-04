package com.flowersofk.sqlitetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler.view.*
import java.text.SimpleDateFormat

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder>() {

    var listData = mutableListOf<RoomMemo>()
    var helper : RoomHelper? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return Holder(view)

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val RoomMemo = listData.get(position)
        holder.setRoomMemo(RoomMemo)

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var mRoomMemo : RoomMemo? = null

        init {
            itemView.buttonDel.setOnClickListener{
                helper?.RoomMemoDao()?.delete(mRoomMemo!!)
                listData.remove(mRoomMemo)
                notifyDataSetChanged()
            }
        }

        fun setRoomMemo(RoomMemo: RoomMemo) {

            this.mRoomMemo = RoomMemo

            itemView.textNo.text = "${RoomMemo.no}"
            itemView.textContent.text = "${RoomMemo.content}"

            var sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            itemView.textDatetime.text = "${sdf.format(RoomMemo.datetime)}"

        }

        override fun onClick(v: View?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

}

