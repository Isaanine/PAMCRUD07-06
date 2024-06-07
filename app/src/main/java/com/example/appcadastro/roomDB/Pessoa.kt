package com.example.appcadastro.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

//Classe que representa a entidade pessoa no banco de dados
@Entity
data class Pessoa(
    val nome: String,
    val telefone: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)
