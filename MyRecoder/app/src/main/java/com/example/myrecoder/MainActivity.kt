package com.example.myrecoder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myrecoder.databinding.ActivityMainBinding
import com.example.myrecoder.model.MyDatabase
import com.example.myrecoder.model.MyRecord
import kotlinx.coroutines.*
import java.time.LocalDateTime


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 기록하기 버튼
        binding.buttonRecord.setOnClickListener {
            val food = binding.editTextFoodName.text.toString()
            if(food.isNotEmpty()) {
                val time:String = LocalDateTime.now().toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val db = MyDatabase.getInstance(this@MainActivity)
                    db?.MyDao()?.insert(MyRecord(0, food, time))
                }
            }
        }

        // 페이지 이동
        binding.buttonHistory.setOnClickListener{
            // 필수로 만들어야 하는 것, 내가 하고자 하는 주문서(intent, MainActivity가 HistoryActivity를 주문한다!)
            val intent = Intent(this, HistoryActivity::class.java)
            // 주문서 전달
            startActivity(intent)
        }
    }

}