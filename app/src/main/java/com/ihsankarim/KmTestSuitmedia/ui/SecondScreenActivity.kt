package com.ihsankarim.KmTestSuitmedia.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.ihsankarim.KmTestSuitmedia.UserViewModel
import com.ihsankarim.KmTestSuitmedia.data.retrofit.ApiConfig
import com.ihsankarim.KmTestSuitmedia.data.repository.UserRepository
import com.ihsankarim.KmTestSuitmedia.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val showName = sharedPreferences.getString("showName", "John Doe")
        binding.tvName.text = showName ?: "John Doe"

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivity(intent)
        }

        val apiService = ApiConfig.getApiService()
        val userRepository = UserRepository(apiService)

        userViewModel = UserViewModel(userRepository)


        val selectedUserName = intent.getStringExtra("selectedUserName")
        binding.tvSelectedUserName.text = selectedUserName ?: "Selected User Name"
        userViewModel.selectedUserName.observe(this) { userName ->
            binding.tvSelectedUserName.text = userName ?: "Selected User Name"
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
            finish()
        }
    }
}