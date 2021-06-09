package com.example.progettomobilecamillonitisenigiri.Main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progettomobilecamillonitisenigiri.ViewModels.FirebaseConnection
import com.example.progettomobilecamillonitisenigiri.Corso.CorsoActivity
import com.example.progettomobilecamillonitisenigiri.Model.Corso
import com.example.progettomobilecamillonitisenigiri.Adapters.CorsoAdapter
import com.example.progettomobilecamillonitisenigiri.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.*


class FragmentCatalogo : Fragment(), CorsoAdapter.OnCorsoListener {
    //ViewModels
    //val corsiModel: CorsiViewModel by viewModels()
    val firebaseConnection: FirebaseConnection by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catalogo, container, false)
        val chipGroup1 = view.findViewById<ChipGroup>(R.id.chipGroupCatalogo1)
        val chipGroup2 = view.findViewById<ChipGroup>(R.id.chipGroupCatalogo2)
        firebaseConnection.getCategorie().observe(viewLifecycleOwner,Observer<Set<String>>{ categorie->
            chipGroup1.removeAllViews()
            chipGroup2.removeAllViews()
            for (i in 0..categorie.size-1) {
                var chip = inflater.inflate(R.layout.chip_catalogo, chipGroup1, false) as Chip
                var chip2 = inflater.inflate(R.layout.chip_catalogo, chipGroup2, false) as Chip
                if (i % 2 == 0) {
                    chip.id = i
                    chip.text = categorie.elementAt(i)
                    chip.setOnClickListener {
                        onclick(view,chip.text.toString())
                    }
                    chipGroup1.addView(chip)
                } else {
                    chip2.id = i
                    chip2.text = categorie.elementAt(i)
                    chip2.setOnClickListener {
                        onclick(view,chip2.text.toString())
                    }
                    chipGroup2.addView(chip2)
                }
            }
        })
        return view
    }


    var list = ArrayList<Corso>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvCat1: RecyclerView = view.findViewById(R.id.recyclerViewCat1)
        val rvCat2: RecyclerView = view.findViewById(R.id.recyclerViewCat2)


        rvCat1.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCat2.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        firebaseConnection.getListaCorsi().observe(viewLifecycleOwner,Observer<List<Corso>>{corsi->
            rvCat1.adapter = CorsoAdapter(corsi, this)
            rvCat2.adapter = CorsoAdapter(corsi, this)
        })






        val editText = view.findViewById<TextInputEditText>(R.id.query)
        editText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = editText.text.toString()
                val action = FragmentCatalogoDirections.actionFragmentCatalogoToFragmentRicerca(
                    query
                )
                view.findNavController().navigate(action)
                return@OnEditorActionListener true
            }
            false
        })
    }


    fun onclick(view: View, cat:String) {
        val action = FragmentCatalogoDirections.actionFragmentCatalogoToFragmentCategoria(cat)
        view.findNavController().navigate(action)
    }

    override fun onCorsoClick(position: Int,v: View?) {
        val intent = Intent(context, CorsoActivity::class.java)
        intent.putExtra("ID_CORSO",v?.findViewById<TextView>(R.id.corsoId)!!.text)
        startActivity(intent)
    }


}