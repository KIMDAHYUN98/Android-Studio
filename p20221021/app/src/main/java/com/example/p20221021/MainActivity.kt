package com.example.p20221021

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.p20221021.databinding.ActivityMainBinding

class MyCycle: DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) {
        Log.i("MainActivity", "Mycycle-Started")
    }
    override fun onStop(owner: LifecycleOwner) {
        Log.i("MainActivity", "Mycycle-Stopped")
    }
}

class MainActivity : AppCompatActivity(), TextWatcher{
    private lateinit var myCycle:MyCycle

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val result = it.resultCode
            Log.d("MainActivity", "Activity result code : $result")
        }

    private val onApply = View.OnClickListener {
        
        var course = when(binding.radioGroup.checkedRadioButtonId) {
            R.id.radioButtonAult -> "성인반"
            else -> "학생반"
        }
        
        val intent = Intent(this, CourseActivity::class.java).apply {
            putExtra("name", binding.editTextTextPersonName.text.toString())
            putExtra("course", course)
        }
        //startActivity(intent)
        getResult.launch(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myCycle = MyCycle()
        lifecycle.addObserver(myCycle)

        binding.progressBar.max=4

        binding.editTextTextPersonName.addTextChangedListener(this)
        binding.buttonApply.setOnClickListener(onApply)
        Log.i("MainActivity", "onCreate")
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        Log.d("TextWatcher", "beforeTextChanged ($s, $start, $count, $after)")
    }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        Log.d("TextWatcher", "onTextChanged ($s, $start, $before, $count)")
    }
    override fun afterTextChanged(s: Editable?) {
        Log.d("TextWatcher", "afterTextChanged (${s.toString()})")
    }



}