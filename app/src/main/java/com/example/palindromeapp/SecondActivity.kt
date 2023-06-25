package com.example.palindromeapp

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.palindromeapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val MY_REQUEST_CODE = 12
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Second Screen"

//        supportActionBar?.displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
//        supportActionBar?.setCustomView(R.layout.action_bar_layout)

        binding.tvNameFirstScreen.text = intent.getStringExtra("palindrome_name")

        binding.button2.setOnClickListener {
            Intent(this@SecondActivity, ThirdActivity::class.java).also {
                startActivityForResult(it, MY_REQUEST_CODE)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if(data != null){
                val bundle = data.extras
                val firstName = bundle!!.getString("first_name")
                val lastName = bundle.getString("last_name")
                binding.tvSelectedUserFirst.text = "$firstName $lastName"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}