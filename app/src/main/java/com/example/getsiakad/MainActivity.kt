package com.example.getsiakad

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var siswaApiService: SiswaApiService
    private lateinit var siswaAdapter: siswaAdapter
    private lateinit var rvSiswa: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvSiswa = findViewById(R.id.rv_siswa)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://siswa.smktelkom-mlg.sch.id/Thefirst_siswa/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        siswaApiService = retrofit.create(SiswaApiService::class.java)

        siswaAdapter = siswaAdapter(emptyList())
        rvSiswa.adapter = siswaAdapter
        rvSiswa.layoutManager = LinearLayoutManager(this)

        getDataSiswa()
    }

    private fun getDataSiswa() {
        siswaApiService.getDataSiswa().enqueue(object : Callback<List<Siswa>> {
            override fun onResponse(call: Call<List<Siswa>>, response: Response<List<Siswa>>) {
                if (response.isSuccessful) {
                    val siswaList = response.body() ?: emptyList()
                    if (siswaList.isEmpty()) {
                        Toast.makeText(this@MainActivity, "Tidak ada data absensi", Toast.LENGTH_SHORT).show()
                    } else {
                        val siswaData = siswaList.map { siswa ->
                            val kelas = "${siswa.nama_kelas} ${siswa.kelompok}"
                            Siswa(nama = siswa.nama, jam = siswa.jam, id_kelas = siswa.id_kelas, nama_kelas = siswa.nama_kelas, kelompok = siswa.kelompok)
                        }
                        siswaAdapter.setData(siswaData)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to get data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Siswa>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to get data: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
