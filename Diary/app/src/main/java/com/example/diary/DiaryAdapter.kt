package com.example.diary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.model.Diary

class DiaryAdapter : RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {
    private var data = listOf<Diary>()
    class DiaryViewHolder(v: View):RecyclerView.ViewHolder(v) {
        val textViewTitle:TextView = v.findViewById(R.id.textViewTitle)
        val textViewContent:TextView = v.findViewById(R.id.textViewContent)
        val textViewTime:TextView = v.findViewById(R.id.textViewTime)
    }

    fun setData(data:List<Diary>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val v:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.diary_history, parent, false)
        return DiaryViewHolder(v)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val item = data[position]

        // 꺼내온 데이터 항목을 ui에다가 덮어씀
        holder.textViewTitle.text = item.title
        holder.textViewContent.text = item.content
        holder.textViewTime.text = item.time
    }

    override fun getItemCount(): Int {
        return data.size
    }
}