package com.example.getsiakad

import retrofit2.Call
import retrofit2.http.GET

interface SiswaApiService {
    @GET("tm_thefirst")
    fun getDataSiswa(): Call<List<Siswa>>
}
