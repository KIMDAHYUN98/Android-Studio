package com.example.listpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listpractice.databinding.ActivityMainBinding
import com.example.listpractice.util.DataGenerator

// 1. Data 클래스 정의
// 2. 아이템 항목 디자인
// 3. Adapter 구현
//  - ViewHolder 구현
//  - 3가지 함수 override
//  - data class의 list 생성 및 함수 추가
// 4. 클릭 이벤트 전달한 interface 정의
// 5. Activity에서 interface 구현

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val adapter=ChatAdapter()
    private val itemTouchCallback =
        object:ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView:RecyclerView,
                viewHolder:RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) : Boolean {
                adapter.swapItem(viewHolder.layoutPosition, target.layoutPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.layoutPosition)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
        adapter.setItemClickListener {
            Log.d("MainActivity", "$it item clicked")
            startActivity(Intent(this, ChatRoomActivity::class.java))
            intent.putExtra("roomId", adapter.getItem(it)?.name)
            startActivity(intent)
        }

        binding.buttonRandom.setOnClickListener {
            val data = DataGenerator.get()
            adapter.setData(data)
        }

        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.recyclerview)
    }
}