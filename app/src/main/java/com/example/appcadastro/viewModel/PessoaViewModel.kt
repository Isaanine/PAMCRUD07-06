package com.example.appcadastro.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.appcadastro.roomDB.Pessoa
import kotlinx.coroutines.launch

class PessoaViewModel(private val repository: Repository): ViewModel() {

    //Recupera todos os registros de pessoa do repositório e os converte em um LiveData
    fun getPessoa() = repository.getAllPessoa().asLiveData(viewModelScope.coroutineContext)

// Insere ou atualiza uma pessoa no repositório
    fun upsertPessoa(pessoa: Pessoa){
        viewModelScope.launch {
            repository.upsertPessoa(pessoa)
        }
    }
//Exclui uma pessoa do repositório
    fun deletePessoa(pessoa: Pessoa){
        viewModelScope.launch {
            repository.deletePessoa(pessoa)
        }
    }
}
