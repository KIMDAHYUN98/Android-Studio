package com.example.diary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.diary.databinding.ActivityWriteBinding
import com.example.diary.model.Diary
import com.example.diary.model.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class WriteActivity : AppCompatActivity() {

    private val binding by lazy { ActivityWriteBinding.inflate(layoutInflater)}

    private val adapter = DiaryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data:String? = intent.getStringExtra("date")

        binding.saveBtn.setOnClickListener {
            var title = binding.editTitle.text.toString()
            var content = binding.editContent.text.toString()
            if(title.isNotEmpty()) {
                val time:String = LocalDateTime.now().toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val db = MyDatabase.getInstance(this@WriteActivity)
                    db?.MyDao()?.insert(Diary(time, title, content))
                }
            }
        }

    }
}