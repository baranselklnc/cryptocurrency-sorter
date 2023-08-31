package com.example.retrofituygulamas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofituygulamas.R
import com.example.retrofituygulamas.model.CryptoModel

class RecyclerViewAdapter(private val cryptoList:ArrayList<CryptoModel>): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    class RowHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(cryptoModel: CryptoModel){

            val textView: TextView = itemView.findViewById(R.id.text_name) // Buradaki R.id.textViewId, layout dosyanızdaki TextView'in ID'si olmalıdır
            val textView2: TextView = itemView.findViewById(R.id.text_price)
            textView.text=cryptoModel.currency
            textView2.text=cryptoModel.price

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
         val view =LayoutInflater.from(parent.context).inflate(R.layout.raw_layout,parent,false)
         return RowHolder(view)

    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

    }
}