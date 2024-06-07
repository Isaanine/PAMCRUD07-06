package com.example.appcadastro.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PessoaDao {

//Upsert (insert ou update) a pessoa no banco de dados
    @Upsert
    suspend fun upsertPessoa(pessoa: Pessoa)

//Deleta uma pessoa do banco
    @Delete
    suspend fun deletePessoa(pessoa: Pessoa)

//Recupe dados da pessoa na tabela
    @Query("SELECT * FROM pessoa")
    fun getAllPessoa(): Flow<List<Pessoa>>
}