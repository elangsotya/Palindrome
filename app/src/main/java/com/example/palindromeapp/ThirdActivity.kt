package com.example.palindromeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.palindromeapp.adapter.MainAdapter
import com.example.palindromeapp.config.ApiConfig
import com.example.palindromeapp.databinding.ActivityThirdBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var adapters: MainAdapter

    private val _isLoading = MutableLiveData<Boolean>()
    private val isLoading: LiveData<Boolean> = _isLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Third Screen"

        adapters = MainAdapter(this@ThirdActivity, arrayListOf())

        binding.rvReview.adapter = adapters
        binding.rvReview.setHasFixedSize(true)
        getUserList()
        isLoading.observe(this){
            showLoading(it)
        }
    }

    private fun getUserList(){
        _isLoading.value = true
        ApiConfig.getApiService().getUserList().enqueue(object:Callback<UserResponse>{
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val data = response.body()?.data
                    setDataAdapter(data!!)
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("Error", ""+t.stackTraceToString())
            }
        })
        adapters.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(data: DataItem) {
        val userSelectedFirst = data.firstName
        val userSelectedLast = data.lastName
        val secondActivity = Intent(this@ThirdActivity, SecondActivity::class.java)
        secondActivity.apply {
            putExtra("first_name", userSelectedFirst)
            putExtra("last_name", userSelectedLast)
        }
        setResult(RESULT_OK, secondActivity)
        finish()
    }

    private fun setDataAdapter(data: ArrayList<DataItem>){
        adapters.setData(data)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}