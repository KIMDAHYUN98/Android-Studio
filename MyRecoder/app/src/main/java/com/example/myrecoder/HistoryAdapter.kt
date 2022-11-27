package com.example.myrecoder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecoder.model.MyRecord

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){
    private var data = listOf<MyRecord>()

    // adapter를 생성할 때는 제일 먼저 해야 될 것 : viewHolder 클래스 생성
    class HistoryViewHolder(v: View):RecyclerView.ViewHolder(v) {
        val textViewFood:TextView = v.findViewById(R.id.textViewFood)
        val textViewTime: TextView =v.findViewById(R.id.textViewTime)
    }

    // 데이터 셋팅
    fun setData(data:List<MyRecord>) {
        this.data = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val v:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = data[position]
        
        // 꺼내온 데이터 항목을 ui에다가 덮어씀
        holder.textViewFood.text = item.food
        holder.textViewTime.text = item.time
    }

    // 항상 코드는 비슷함
    override fun getItemCount(): Int {
        // 데이터에서 항목을 꺼냄
       return data.size
    }
}