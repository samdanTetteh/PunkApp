package com.ijikod.punkapp

import com.ijikod.punkapp.Model.Beer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


fun getBeersFromNetwork(
    service: PunkService,
    page: Int,
    itemsPerPage: Int,
    onSuccess: (repos: List<Beer>) -> Unit,
    onError: (error: String) -> Unit
) {
    service.getAllBears(page, itemsPerPage).enqueue(object : Callback<List<Beer>> {
        override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
            onError(t.message ?: "unknown error")
        }

        override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
            if (response.isSuccessful){
                val beers = response.body()?.sortedBy { it.name } ?: emptyList()
                onSuccess(beers)
            }else{
                onError(response.errorBody()?.string() ?: "Unknown error")
            }

        }

    })

}


interface PunkService {

    @GET("beers?")
    fun getAllBears(@Query("page") page: Int, @Query("per_page") itemsPerPage : Int) : Call<List<Beer>>


    companion object{
        private const val BASE_URL = "https://api.punkapi.com/v2/"

        fun create(): PunkService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(PunkService::class.java)
        }

    }
}