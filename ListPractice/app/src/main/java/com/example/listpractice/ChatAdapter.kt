package com.example.listpractice

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listpractice.util.ChatRoomInfo
import java.util.*

class ChatAdapter:RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    class ChatViewHolder(v: View, listener: OnListItemClickListener?):RecyclerView.ViewHolder(v) {
        val textViewName:TextView = v.findViewById(R.id.textViewName)
        val textViewTime:TextView = v.findViewById(R.id.textViewTime)
        val imageView: ImageView = v.findViewById(R.id.imageView)

        init {
            v.setOnClickListener {
                Log.d("List", "$layoutPosition clicked")
                listener?.onItemClick(layoutPosition)
            }
        }
    }

    // 클릭이 발생한 인덱스를 넘겨줌
    fun interface OnListItemClickListener {
        fun onItemClick(position:Int)
    }


    private var listener:OnListItemClickListener? = null

    // 아이템 추가 및 삭제
    private var data = mutableListOf<ChatRoomInfo>()

    fun setItemClickListener(listener: OnListItemClickListener) {
        this.listener = listener
    }

    // 데이터를 mainActivity에 받아서 입력
    fun setData(data:MutableList<ChatRoomInfo>) {
        this.data = data
        // recycler 부분 뿐만 아니라 처음부터 끝까지 바꿔줌
        notifyDataSetChanged()
    }

    fun swapItem(from:Int, to:Int) {
        Collections.swap(data, from, to)
        notifyItemChanged(from, to)
    }

    fun removeItem(index:Int) {
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun getItem(index:Int):ChatRoomInfo? {
        if(index in 0 .. data.lastIndex)
            return data[index]
        else return null
    }

    // 10~20 개 만들어질 예정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val v:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_list, parent, false)
        return ChatViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val item = data[position]
        holder.imageView.setImageResource(item.image)
        holder.textViewName.text = item.name
        holder.textViewTime.text = item.time
    }

    override fun getItemCount(): Int {
         return data.size
    }

}