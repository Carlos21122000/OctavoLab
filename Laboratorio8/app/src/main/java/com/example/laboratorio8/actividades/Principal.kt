package com.example.laboratorio8.actividades

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.laboratorio8.R
import com.example.laboratorio8.databinding.PrincipalFragmentBinding
import kotlinx.android.synthetic.main.principal_fragment.*


class Principal : Fragment() {

    var boolean:Boolean = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding=PrincipalFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner (this)
        binding.viewModels=viewModel

        //For the search button
        binding.button.setOnClickListener {
            viewModel.valort=binding.editText.text.toString()
            viewModel.getGithubProperties()

        }

        viewModel.status.observe(this, Observer { status ->
            status?.let {
                viewModel.status.value = null
                if(status==false){
                    binding.imageView.visibility=View.VISIBLE
                    binding.button2.visibility=View.VISIBLE
                    boolean=true
                }else if(status==true){

                    binding.imageView.visibility=View.GONE
                    binding.button2.visibility=View.GONE
                    boolean=false
                    Toast.makeText(activity,"Error al buscar el usuario",Toast.LENGTH_SHORT).show()
                }
            }
        })

        if (boolean==false){
            binding.button2.visibility=View.GONE
        }else{
            binding.button2.visibility=View.VISIBLE
        }

        //For the repositories button
        binding.button2.setOnClickListener {

            if (binding.editText.text.isNotBlank()){
                if (viewModel.responsa.value=="No se encutra"){
                    binding.button2.visibility=View.GONE
                }else{
                    binding.button2.visibility=View.VISIBLE
                    var comment=editText.text.toString()

                    binding.button2.visibility=View.GONE

                    var bundle1= bundleOf("comment" to comment)
                    view!!.findNavController().navigate(R.id.action_principal_to_recyclerFragment,bundle1)
                    boolean=true
                }

            }else{
                Toast.makeText(activity,"No",Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root

    }
    private val viewModel: PrincipalViewModel by lazy {
        ViewModelProviders.of(this).get(PrincipalViewModel::class.java)
    }

}
