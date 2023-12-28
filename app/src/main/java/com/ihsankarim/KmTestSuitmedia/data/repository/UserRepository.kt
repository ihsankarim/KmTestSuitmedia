package com.ihsankarim.KmTestSuitmedia.data.repository

import com.ihsankarim.KmTestSuitmedia.data.model.UserResponse
import com.ihsankarim.KmTestSuitmedia.data.retrofit.ApiService
import retrofit2.Call

class UserRepository(private val apiService: ApiService) {
    fun getUsers(page: Int, perPage: Int): Call<UserResponse> {
        return apiService.getUsers(page, perPage)
    }
}