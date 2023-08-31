package com.example.retrofituygulamas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.retrofituygulamas.R
import com.example.retrofituygulamas.adapter.RecyclerViewAdapter
import com.example.retrofituygulamas.model.CryptoModel
import com.example.retrofituygulamas.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener {

    private val BASE_URL="https://raw.githubusercontent.com/"
    private var cryptoModels:ArrayList<CryptoModel>?=null
    private var recyclerViewAdapter:RecyclerViewAdapter?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        val recyclerView: RecyclerView = findViewById(R.id.recylerView) // Initialize RecyclerView

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager // Set the layoutManager
        loadData()
    }

    private fun  loadData(){
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //retrofit oluşturucuya base url verildi ve gson formatında bölmek iiçn gsonconverterfactroy çalıştırıdlı
        val service=retrofit.create(CryptoAPI::class.java) // api ile retrofiti birbirine bağlamak için
        val call=service.getData()

        call.enqueue(object : Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { it ->//response.body()?. == null değilse aşağıyı çalıştır

                    cryptoModels=ArrayList(it)
                      cryptoModels?.let {
                          recyclerViewAdapter=RecyclerViewAdapter(it,this@MainActivity)
                          val recyclerView: RecyclerView = findViewById(R.id.recylerView) // Initialize RecyclerView

                          recyclerView.adapter=recyclerViewAdapter
                      }

                    }

                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
               t.printStackTrace()
            }


        }) //asyncrohonus seklinde verileri alır
    }

    override fun OnItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked: ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }

}