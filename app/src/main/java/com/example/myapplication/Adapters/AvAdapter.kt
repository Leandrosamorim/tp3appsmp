package com.example.myapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.Avaliacao
import com.example.myapplication.Models.CriptoConverter
import com.example.myapplication.Models.Criptografador
import com.example.myapplication.Models.ListConverter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.avaliacao_card.view.*

class AvAdapter (
    private val listaAvaliacoes : List<Avaliacao>
)
    : RecyclerView.Adapter<AvAdapter.AvaliacaoViewHolder>()
{

    class AvaliacaoViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {
        val empresa : TextView =  itemView.txtEmpresa
        val bairro  : TextView = itemView.txtBairro
        val r1 : TextView = itemView.r1
        val r2 : TextView = itemView.r2
        val r3 : TextView = itemView.r3
        val r4 : TextView = itemView.r4
        val r5 : TextView = itemView.r5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvaliacaoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.avaliacao_card, parent, false)
        val avaliacaoViewHolder = AvaliacaoViewHolder(view)
        return  avaliacaoViewHolder
    }

    override fun onBindViewHolder(holder: AvaliacaoViewHolder, position: Int) {
        val avaliacao = listaAvaliacoes[position]
        holder.empresa.text = CriptoConverter().fromCriptoString(avaliacao.empresa)
        holder.bairro.text = CriptoConverter().fromCriptoString(avaliacao.bairro)
        holder.r1.text = ListConverter().stringToList(avaliacao.respostas)?.get(0)
        holder.r2.text = ListConverter().stringToList(avaliacao.respostas)?.get(1)
        holder.r3.text = ListConverter().stringToList(avaliacao.respostas)?.get(2)
        holder.r4.text = ListConverter().stringToList(avaliacao.respostas)?.get(3)
        holder.r5.text = ListConverter().stringToList(avaliacao.respostas)?.get(4)

    }
    override fun getItemCount(): Int = listaAvaliacoes.size
}