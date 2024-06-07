package com.example.appcadastro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.appcadastro.roomDB.Pessoa
import com.example.appcadastro.roomDB.PessoaDataBase
import com.example.appcadastro.ui.theme.AppCadastroTheme
import com.example.appcadastro.viewModel.PessoaViewModel
import com.example.appcadastro.viewModel.Repository


class MainActivity : ComponentActivity() {
    private  val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            PessoaDataBase::class.java,
            "pessoa.db"
        ).build()
    }

    private val viewModel by viewModels<PessoaViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T: ViewModel> create(modelClass: Class<T>): T{
                    return PessoaViewModel(Repository(db))as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCadastroTheme {
                //Background theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(viewModel)
                }
            }
        }
    }
}

@Composable
fun App(viewModel: PessoaViewModel){
    //Variáveis para armazenar os valores dos campos
    var nome by remember{
        mutableStateOf("")
    }

    var telefone by remember{
        mutableStateOf("")
    }
//Cria uma nova pessoa com os valores dos campos
    val pessoa= Pessoa(
        nome,
        telefone
    )
//================================
    Column(
        Modifier
            .background(Color.White)
    ) {

//================================
        Row(
            Modifier
                .background(Color.White)
                .fillMaxWidth(),
            Arrangement.Center
        ){
            Text(
                text = "App Cadastro Cliente",
                fontFamily =  FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )

        }
//================================

//Campos

//Campo Nome
        Row(
            Modifier
                .padding(20.dp)
        ) {

        }
        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ){
            TextField(
                value = nome,
                onValueChange ={ nome = it },
                label  = { Text(text = "Nome")}
            )
        }

//===============================
//Campo Telefone
        Row(
            Modifier
                .padding(20.dp)
        ) {

        }

        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ){
            TextField(
                value = telefone,
                onValueChange ={ telefone = it },
                label  = { Text(text = "Telefone") }
            )
        }

//================================


//Button
        Row(
            Modifier
                .padding(20.dp)
        ) {

        }

        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center
        ) {
            Button(
                onClick = {
                    viewModel.upsertPessoa(pessoa)
                }
            ) {
                Text(text = "Cadastrar")
            }







        }
    }
}
