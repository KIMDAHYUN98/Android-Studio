package com.example.final2101257

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final2101257.databinding.ActivityMainBinding
import com.example.final2101257.model.MyDatabase
import com.example.final2101257.model.MyTodo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter = TodoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter

        binding.saveBtn.setOnClickListener{
            var content = binding.editInputTodo.text.toString()
            if(content.isNotEmpty()) {
                var time:String = LocalDateTime.now().toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val db = MyDatabase.getInstance(this@MainActivity)
                    db?.MyDao()?.insert(MyTodo(0, time, content))
                }
            }
        }

    }

}