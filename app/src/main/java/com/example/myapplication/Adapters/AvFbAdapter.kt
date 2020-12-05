package com.example.myapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Models.Avaliacao
import com.example.myapplication.Models.AvaliacaoFB
import com.example.myapplication.Models.CriptoConverter
import com.example.myapplication.Models.ListConverter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.av_fb_card.view.*
import kotlinx.android.synthetic.main.avaliacao_card.view.*
import kotlinx.android.synthetic.main.avaliacao_card.view.txtBairro
import java.util.*


class AvFbAdapter (
    private val listaAvaliacoes : List<AvaliacaoFB>
)
    : RecyclerView.Adapter<AvFbAdapter.AvaliacaoViewHolder>()
{

    class AvaliacaoViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {
        val bairro  : TextView = itemView.txtBairro
        val r1 : TextView = itemView.r
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvaliacaoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.av_fb_card, parent, false)
        val avaliacaoViewHolder = AvaliacaoViewHolder(view)
        return  avaliacaoViewHolder
    }

    override fun onBindViewHolder(holder: AvFbAdapter.AvaliacaoViewHolder, position: Int) {
        val avaliacao = listaAvaliacoes[position]
        var avgRating = avaliacao.avg!! * 100
        holder.bairro.text = avaliacao.bairro
        holder.r1.text = "Possui avaliações $avgRating% positivas"

    }
    override fun getItemCount(): Int = listaAvaliacoes.size
}
