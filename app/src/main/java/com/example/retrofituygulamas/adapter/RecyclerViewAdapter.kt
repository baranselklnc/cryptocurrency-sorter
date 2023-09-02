package com.example.retrofituygulamas.adapter

import android.graphics.Color
import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofituygulamas.R
import com.example.retrofituygulamas.model.CryptoModel
import okhttp3.internal.http2.Http2Connection

class RecyclerViewAdapter(private val cryptoList:ArrayList<CryptoModel>,private val listener:Listener): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
 interface Listener {
     fun OnItemClick(cryptoModel: CryptoModel)
 }

   private val colors:Array<String> = arrayOf("#7a53f9","#c28bbb","#21b2b6","#f7aa1f","#f953d2","#f71f6c","#d2f953","#5866ff","#5b235c")



    class RowHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(cryptoModel: CryptoModel,colors:Array<String>,position: Int,listener:Listener){
            itemView.setOnClickListener {
                listener.OnItemClick(cryptoModel)
            }

            val textView: TextView = itemView.findViewById(R.id.text_name) // Buradaki R.id.textViewId, layout dosyanızdaki TextView'in ID'si olmalıdır
            val textView2: TextView = itemView.findViewById(R.id.text_price)
           // val cardview:CardView=itemView.findViewById(R.id.cardView)
            textView.text=cryptoModel.currency
            textView2.text=cryptoModel.price
          itemView.setBackgroundColor(Color.parseColor(colors[position%9]) )

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
        holder.bind(cryptoList[position],colors,position,listener)

    }
}