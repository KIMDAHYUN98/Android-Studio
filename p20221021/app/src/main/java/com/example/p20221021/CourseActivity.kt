package com.example.p20221021

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.result.contract.ActivityResultContracts
import com.example.p20221021.databinding.ActivityCourseBinding

class CourseActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCourseBinding.inflate(layoutInflater)
    }
    private var name:String? = null
    private var course:String? = null

    private val requestPhoto =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.imageViewPhoto.setImageURI(it)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setResult(RESULT_CANCELED)

        with(intent) {
            name = getStringExtra("name")
            name?.let { binding.textViewName.text = it}
            course = getStringExtra("course")
            course?.let { binding.textViewCourse.text = it}
        }

        binding.buttonCancel.setOnClickListener { finish() }
        binding.buttonConfirm.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
        binding.buttonPhoto.setOnClickListener {
            requestPhoto.launch("image/*")
        }

        if(intent.type?.startswith("image/")==true) {
            (intent.getParcelableExtra<Parcelable>(intent.EXTRA_STEAM) as? Uri)?.let {
                binding.imageViewPhoto.setImageURI(it)
            }
        }

    }
}