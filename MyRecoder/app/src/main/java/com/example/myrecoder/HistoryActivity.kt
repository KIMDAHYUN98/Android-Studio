package com.example.myrecoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecoder.databinding.ActivityHistoryBinding
import com.example.myrecoder.model.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {
    // 다른 화면을 쓸 경우 mainActivity가 아닌 ActivityHistoryBinding(=activity_history.xml) 사용

    private val binding by lazy { ActivityHistoryBinding.inflate(layoutInflater)}

    private val adapter = HistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            // mainactivity에서 context는 this로 지정
            // 여기는 this라고 하면 CoroutineScope 이다
            // 함수 호출이 아닌 객체가 새로 생성이 된 것
            // context가 아님, activity만 가질 자격이 있음
            val dao = MyDatabase.getInstance(this@HistoryActivity).MyDao()
            val list = dao.selectAll()
            withContext(Dispatchers.Main) {
                adapter.setData(list)
            }
            list.forEach {
                Log.d("List", it.toString())
            }
        }
    }
}