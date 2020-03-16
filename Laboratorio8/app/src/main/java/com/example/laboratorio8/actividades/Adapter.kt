package com.example.laboratorio8.actividades

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio8.R


class Adapter internal constructor(context: Context): RecyclerView.Adapter<Adapter.ViewHolderData>() {
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    lateinit var reposList: List<ReposProperty>
    private lateinit var itemView: View;
    var conteo = 0


    internal fun setRepos(repositories: List<ReposProperty>) {
        reposList = repositories
        conteo = reposList.size

        notifyDataSetChanged()
    }

    override fun getItemCount() = conteo

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val repo = reposList[position]

        holder.questionItenView.text = repo.name
        holder.itemView.setOnClickListener{

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${repo.repoSrcUrl}"))
            itemView.context.startActivity(intent)


        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        itemView = inflater.inflate(R.layout.item_list, parent, false)
        return ViewHolderData((itemView))
    }
    //ViewHolder for the data
    inner class ViewHolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionItenView: TextView = itemView.findViewById(R.id.repositorio_name)
        val pregunta: TextView = itemView.findViewById(R.id.description_repositorio)



    }

}
