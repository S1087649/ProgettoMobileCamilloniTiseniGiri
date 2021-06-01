package com.example.progettomobilecamillonitisenigiri

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentHome: Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val corso1:Corsi = Corsi()
        val corso2:Corsi = Corsi()
        val corso3:Corsi = Corsi()
        val corso4:Corsi = Corsi()
        val corso5:Corsi = Corsi()
        val rvPopolari: RecyclerView = view.findViewById(R.id.recyclerViewPopolari)
        rvPopolari.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rvPopolari.adapter = PopularAdapter(mutableListOf<Corsi>(corso1,corso2,corso3,corso4,corso5))
        val rvConsigliati: RecyclerView = view.findViewById(R.id.recyclerViewConsigliati)
        rvConsigliati.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rvConsigliati.adapter = PopularAdapter(mutableListOf<Corsi>(corso1,corso2,corso3,corso4,corso5))
        val rvRecenti: RecyclerView = view.findViewById(R.id.recyclerViewRecenti)
        rvRecenti.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        rvRecenti.adapter = PopularAdapter(mutableListOf<Corsi>(corso1,corso2,corso3,corso4,corso5))
    }
}