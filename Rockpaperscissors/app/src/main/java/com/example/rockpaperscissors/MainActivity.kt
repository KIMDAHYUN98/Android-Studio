package com.example.rockpaperscissors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.rockpaperscissors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
        var victory : Int = 0
        var defeat : Int = 0
        var draw : Int = 0
        var number : Int = 0

        private val binding by lazy {
            ActivityMainBinding.inflate(layoutInflater)
        }

    // 가위바위보 과정
        private fun  process(num : Int) {
            val finger = num
            val number = (0 .. 2).random()
            if(finger == number) {
                draw++
                binding.result.text="     무"
                if(number == 0) {
                    binding.result2.text="묵"
                } else if(number == 1) {
                    binding.result2.text="찌"
                } else {
                    binding.result2.text="빠"
                }
            } else if(finger - number == 1 || finger - number == 2) {
                victory++
                binding.result.text="     승"
                if(number == 0) {
                    binding.result2.text="묵"
                } else if(number == 1) {
                    binding.result2.text="찌"
                } else {
                    binding.result2.text="빠"
                }
            } else {
                defeat++
                binding.result.text="     패"
                if(number == 0) {
                    binding.result2.text="묵"
                } else if(number == 1) {
                    binding.result2.text="찌"
                } else {
                    binding.result2.text="빠"
                }
            }
            binding.gamescope.text="${victory}승 ${draw}무 ${defeat}패"
        }
    
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)

            binding.gamescope.text="${victory}승 ${draw}무 ${defeat}패"

            // 가위
            binding.button.setOnClickListener(View.OnClickListener {
                process(0)
            })
            // 바위
            binding.button2.setOnClickListener(View.OnClickListener {
                process(1)
            })
            // 보
            binding.button3.setOnClickListener(View.OnClickListener {
                process(2)
            })
            // 초기화
            binding.button4.setOnClickListener(View.OnClickListener {
                victory = 0
                defeat = 0
                draw = 0
                binding.result.text="      -"
                binding.gamescope.text="${victory}승 ${draw}무 ${defeat}패"
            })
        }

}