package com.example.retrofituygulamas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofituygulamas.R
import com.example.retrofituygulamas.model.CryptoModel
import com.example.retrofituygulamas.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL="https://raw.githubusercontent.com/"
    private var cryptoModels:ArrayList<CryptoModel>?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
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
                    response.body()?.let {//response.body()?. == null değilse aşağıyı çalıştır

                    cryptoModels=ArrayList(it)

                        for (cryptoModel:CryptoModel in cryptoModels!!){
                            print(cryptoModel.currency)
                            print(cryptoModel.price)

                        }
                    }

                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
               t.printStackTrace()
            }


        }) //asyncrohonus seklinde verileri alır
    }

}