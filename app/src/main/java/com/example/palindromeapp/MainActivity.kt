package com.example.palindromeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.palindromeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.nextButton.setOnClickListener {
            val userName = binding.edName.text.toString()
            val secondActivity = Intent(this@MainActivity, SecondActivity::class.java)
                secondActivity.apply {
                    putExtra("palindrome_name", userName)
            }
            startActivity(secondActivity)
        }

        binding.checkButton.setOnClickListener {
            val palindromeName = binding.edName.text.toString()
            if (isPalindrome(palindromeName)){
                binding.tvPalindromeResult.text = "isPalindrome"
            } else{
                binding.tvPalindromeResult.text = "not palindrome"
            }
        }
    }

    private fun isPalindrome(value: String):Boolean{
        var result: Boolean = true
        val newValue = value.replace("\\s".toRegex(),"")
        for (i in newValue.length-1 downTo 0){
            if(newValue[i] != newValue[newValue.length-1-i]){
                result = false
                break
            }
        }
        return result
    }
}