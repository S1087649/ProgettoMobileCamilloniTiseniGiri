package com.example.progettomobilecamillonitisenigiri.ViewModels

import androidx.lifecycle.*
import com.example.progettomobilecamillonitisenigiri.Model.Corso
import com.example.progettomobilecamillonitisenigiri.Model.Documento
import com.example.progettomobilecamillonitisenigiri.Model.Lezione
import com.example.progettomobilecamillonitisenigiri.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FirebaseConnection : ViewModel() {

    private lateinit var database: DatabaseReference
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mDatabaseReference: DatabaseReference
//    private var  loggedUser: FirebaseUser?
    private var controllerCorsi:CorsiViewModel = CorsiViewModel()
    private var controllerUser:UserViewModel = UserViewModel()

    private val listCorsi = MutableLiveData<List<Corso>>()
    private val listAggiuntiDiRecente = MutableLiveData<List<Corso>>()
    private val listCategorie = MutableLiveData<Set<String>>()
    private val listLezioni = MutableLiveData<HashMap<String, ArrayList<Lezione>>>()
    private val listDispense = MutableLiveData<HashMap<String, ArrayList<Documento>>>()
    private val listaConsigliati = MutableLiveData<List<Corso>>()

    private val currentCourse = MutableLiveData<Corso>()
    private val categoriePreferite = MutableLiveData<List<String>>()
    private val currentUser = MutableLiveData<User>()

    private val lista_corsi = ArrayList<Corso>()
    private val lista_lezioni = HashMap<String, ArrayList<Lezione>>()
    private val lista_cat = HashSet<String>()
    private val lista_dispense = HashMap<String, ArrayList<Documento>>()

    init {
        database = FirebaseDatabase.getInstance().reference
        mDatabaseReference = FirebaseDatabase.getInstance().reference
        readDataUser()
    }
/*
    fun readUtente(snapshot: DataSnapshot) {

        if (snapshot.child("Users").child(loggedUser!!.uid)!!.exists()) {
            val utenteSnap = snapshot.child("Users").child(loggedUser!!.uid)
            val utente = utenteSnap.getValue(User::class.java)
            currentUser.postValue(utente)
            categoriePreferite.postValue(utente!!.categoriePref)
        }

    }

    fun setUtente(firstName: String, lastName: String,) {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")

        loggedUser.let { it1 ->
            mDatabaseReference!!.child(it1!!.uid).child("firstName")
                .setValue(firstName)
        }
        loggedUser.let { it1 ->
            mDatabaseReference!!.child(it1!!.uid).child("lastName")
                .setValue(lastName)
        }


    }
    fun setUtente(utente: User) {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")

        loggedUser.let { it1 ->
            mDatabaseReference!!.child(it1!!.uid).setValue(utente)
        }


    }
*/

    fun readDataUser() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                /*lista_corsi.clear()
                lista_lezioni.clear()
                lista_cat.clear()
                lista_dispense.clear()*/
                controllerUser.readUtente(snapshot)
                /*if (snapshot.child("Corsi")!!.exists()) {
                    for (e in snapshot.child("Corsi").children) {
                        val corso = e.getValue(Corso::class.java)
                        val cat = e.child("categoria").value.toString()
                        val tmp_list = ArrayList<Lezione>()
                        val tmp_list_dispense = ArrayList<Documento>()
                        lista_cat.add(cat)
                        lista_corsi.add(corso!!)
                        for (lezione in e.child("lezioni").children) {
                            val l = lezione.getValue(Lezione::class.java)
                            if (l != null) tmp_list.add(l)
                        }
                        lista_lezioni.put(corso.id, tmp_list)
                        for (documento in e.child("dispense").children) {
                            val l = documento.getValue(Documento::class.java)
                            if (l != null) tmp_list_dispense.add(l)
                        }
                        lista_dispense.put(corso.id, tmp_list_dispense)
                    }
                    //inserisco il valore nelle mutableLiveData
                    listLezioni.postValue(lista_lezioni)
                    listCorsi.postValue(lista_corsi)
                    listAggiuntiDiRecente.postValue(lista_corsi.reversed())//oppure takeLast(numero)
                    listCategorie.postValue(lista_cat)
                    listDispense.postValue(lista_dispense)
                }
                */
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }
/*
    fun getUser(): MutableLiveData<User> {
        return currentUser
    }

    fun getListaCorsi(): MutableLiveData<List<Corso>> {
        return listCorsi
    }

    //Funzione che ritorna lista consigliati iterando una Lista di Corsi
    //Non utilizza liveData
    fun getListaConsigliati(corsi: ArrayList<Corso>): ArrayList<Corso> {
        val consigliati = ArrayList<Corso>()
        for (corso in corsi) {
            for (categoria in currentUser.value!!.categoriePref) {
                if (corso.categoria.equals(categoria)) {
                    consigliati.add(corso)
                }
            }

        }
        if(consigliati.isEmpty())
            return corsi //ritorna corsi se l'utente non ha categorie predefinite
        return consigliati
    }

    fun getAggiuntiDiRecente(): MutableLiveData<List<Corso>> {
        return listAggiuntiDiRecente
    }

    fun getListaLezioni(): MutableLiveData<HashMap<String, ArrayList<Lezione>>> {
        System.out.println("EntraLezione")
        return listLezioni
    }

    fun getCategorie(): MutableLiveData<Set<String>> {
        System.out.println("EntraCategoria")
        return listCategorie
    }

    fun getListaDispense(): MutableLiveData<HashMap<String, ArrayList<Documento>>> {
        System.out.println("EntraDispense")
        return listDispense
    }
    */
}
