package com.example.getsiakad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class siswaAdapter(private var siswaList: List<Siswa>) : RecyclerView.Adapter<siswaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): siswaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_siswa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: siswaAdapter.ViewHolder, position: Int) {
       val siswa = siswaList[position]
        val kelas = "${siswa.nama_kelas} ${siswa.kelompok}"
        holder.tvNama.text = siswa.nama
        holder.tvKelas.text = kelas
        holder.tvJam.text = siswa.jam
    }

    override fun getItemCount(): Int {
        return siswaList.size
    }

    fun setData(siswaData: List<Siswa>) {
        siswaList = siswaData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvKelas: TextView = itemView.findViewById(R.id.tvKelas)
        val tvJam: TextView = itemView.findViewById(R.id.tvJam)
    }

}