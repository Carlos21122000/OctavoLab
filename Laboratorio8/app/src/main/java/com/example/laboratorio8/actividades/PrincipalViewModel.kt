package com.example.laboratorio8.actividades

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laboratorio8.actividades.ApiServices
import com.example.laboratorio8.actividades.GitPropertyClass
import retrofit2.Call
import retrofit2.Response


class PrincipalViewModel : ViewModel() {
    private val _property = MutableLiveData<GitPropertyClass>()
    val property: LiveData<GitPropertyClass>
        get() = _property
    private val _response = MutableLiveData<String>()
    var valort:String=""
    var status = MutableLiveData<Boolean?>()
    val responsa: LiveData<String>
        get() = _response

    fun getGithubProperties() {
        ApiServices.retrofitService.getProperties(valort).enqueue( object: retrofit2.Callback<GitPropertyClass> {


            override fun onResponse(call: Call<GitPropertyClass>, response: Response<GitPropertyClass>){

                _response.value = "User Name: "+response.body()?.login
                if (response.body()?.login!=null){
                    _response.value = "User name: "+response.body()?.login
                    _property.value=response.body()
                    status.value=false
                }else{
                    _response.value = "dont exist"
                    status.value = true
                }
            }
            override fun onFailure(call: Call<GitPropertyClass>, t: Throwable) {
                _response.value = "Error " + t.message
            }
        })
    }

}
