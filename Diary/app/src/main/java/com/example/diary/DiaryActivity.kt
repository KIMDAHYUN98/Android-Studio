package com.example.diary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diary.model.MyDatabase
import com.example.diary.databinding.ActivityDiaryBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class DiaryActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDiaryBinding.inflate(layoutInflater)}

    private val adapter = DiaryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            // mainactivity에서 context는 this로
            val dao = MyDatabase.getInstance(this@DiaryActivity).MyDao()
            val list = dao.selectAll()
            withContext(Dispatchers.Main) {
                adapter.setData(list)
            }
            list.forEach {
                Log.d("List", it.toString())
            }
        }

     /*   fun convertString(date: Date, title: String, num: Int): String {
            val format = SimpleDateFormat("yyyyMMdd")
            return "${format.format(date)}_${title}_${num}"
        }*/

    }
}
