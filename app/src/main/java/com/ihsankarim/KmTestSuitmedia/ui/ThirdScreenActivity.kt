package com.ihsankarim.KmTestSuitmedia.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihsankarim.KmTestSuitmedia.ItemUserAdapter
import com.ihsankarim.KmTestSuitmedia.UserViewModel
import com.ihsankarim.KmTestSuitmedia.data.retrofit.ApiConfig
import com.ihsankarim.KmTestSuitmedia.data.repository.UserRepository
import com.ihsankarim.KmTestSuitmedia.databinding.ActivityThirdScreenBinding

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = ApiConfig.getApiService()
        val userRepository = UserRepository(apiService)

        val adapter = ItemUserAdapter { selectedUserName ->
            userViewModel.setSelectedUserName(selectedUserName)
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra("selectedUserName", selectedUserName)
            startActivity(intent)
        }

        userViewModel = UserViewModel(userRepository)

        userViewModel.getUsers(1, 10)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
        userViewModel.userList.observe(this) { userList ->
            adapter.submitList(userList)
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}