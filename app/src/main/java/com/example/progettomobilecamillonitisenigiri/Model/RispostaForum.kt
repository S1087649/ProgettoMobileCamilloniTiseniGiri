package com.example.progettomobilecamillonitisenigiri.Model

class RispostaForum(val nomeUtente:String,val cognomeUtente:String,var Risposta:String) {
    constructor() : this("Utente", "Utente", "Assente")
    override fun equals(other: Any?): Boolean {
        return if(other is RispostaForum) {
            (other.Risposta.equals(Risposta, true))
        } else false
    }
}