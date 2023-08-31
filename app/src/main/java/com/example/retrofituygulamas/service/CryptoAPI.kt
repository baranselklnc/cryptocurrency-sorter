package com.example.retrofituygulamas.service

import com.example.retrofituygulamas.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
// https://raw.githubusercontent.com/
// atilsamancioglu/K21-JSONDataSet/master/crypto.json
    //get , post , put, delete
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")

    fun getData():Call<List<CryptoModel>> // senkron veri gelecek ve bu liste şekilde ve modelde tasarlandığı gibi


}