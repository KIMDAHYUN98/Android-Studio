package com.example.final2101257

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.final2101257.model.MyTodo

class TodoAdapter:RecyclerView.Adapter<TodoAdapter.TodoViewHoler>() {
    private var data = listOf<MyTodo>()
    class TodoViewHoler(v:View):RecyclerView.ViewHolder(v) {
        val textViewTime: TextView = v.findViewById(R.id.textViewTime)
        val textViewTodo:TextView = v.findViewById(R.id.textViewTodo)
    }

    fun updateData(data:List<MyTodo>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHoler {
        val v:View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return TodoViewHoler(v)
    }

    override fun onBindViewHolder(holder: TodoViewHoler, position: Int) {
        val item = data[position]
        holder.textViewTodo.text = item.content
        holder.textViewTime.text = item.time

    }

    override fun getItemCount(): Int {
        return data.size
    }
}