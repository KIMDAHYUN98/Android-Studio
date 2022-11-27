package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.helloworld.databinding.ActivityMainBinding
import java.lang.NumberFormatException
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater )}
    // private lateinit var binding:ActivityMainBinding

    private val listener = View.OnClickListener {
        try {
            val number1 = binding.editTextNumber1.text.toString().toDouble()
            val number2 = binding.editTextNumber2.text.toString().toDouble()
            binding.textViewResult.text = (number1 - number2).toString()
        } catch (e: NumberFormatException) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener(this)
        binding.buttonSub.setOnClickListener(listener)
        binding.buttonMul.setOnClickListener(View.OnClickListener {
            try {
                val number1 = binding.editTextNumber1.text.toString().toDouble()
                val number2 = binding.editTextNumber2.text.toString().toDouble()
            } catch (e : NumberFormatException) {

            }
        })

        binding.buttonDiv.setOnClickListener {
            try {
                val number1 = binding.editTextNumber1.text.toString().toDouble()
                val number2 = binding.editTextNumber2.text.toString().toDouble()

                if(number2 != 0.0)
                    binding.textViewResult.text = (number1 / number2).toString()
                else
                    binding.textViewResult.text = "0"
            } catch (e: NumberFormatException) {

            }
        }
    }

    override fun onClick(p0: View?) {
        try {
            val number1 = binding.editTextNumber1.text.toString().toDouble()
            val number2 = binding.editTextNumber2.text.toString().toDouble()
            binding.textViewResult.text=(number1+number2).toString()
        } catch (e: NumberFormatException) {
            return
        }
    }
}