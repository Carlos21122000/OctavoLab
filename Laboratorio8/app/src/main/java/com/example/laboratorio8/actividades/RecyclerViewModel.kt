package com.example.laboratorio8.actividades

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laboratorio8.actividades.ApiServices
import com.example.laboratorio8.actividades.ReposProperty
import retrofit2.Call
import retrofit2.Response


class RecyclerViewModel : ViewModel() {

    private var _response = MutableLiveData<List<ReposProperty>>()
    var brt:Boolean=false

    var valor:String="carlos21122000"




    //Get the values of the retrofit service
    fun getReposProperties() {

        ApiServices.retrofitService.getPropertiesRepo(valor).enqueue(object : retrofit2.Callback<List<ReposProperty>>{

            override fun onResponse(
                call: Call<List<ReposProperty>>,
                response: Response<List<ReposProperty>>
            ) {

                _response.value=response.body()!!

            }
            override fun onFailure(call: Call<List<ReposProperty>>, t: Throwable) {
                Log.i("fail","${t.message}")
            }



        })

    }
    val responsa: LiveData<List<ReposProperty>>
        get() = _response
}
