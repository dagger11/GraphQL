package com.ppk.graphql.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ppk.graphql.CharacterQuery
import com.ppk.graphql.R
import com.ppk.graphql.presentation.contract.ViewContract
import com.ppk.graphql.presentation.viewModel.DetailActivityVIewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class DetailActivity : AppCompatActivity(),ViewContract {
    lateinit var characterPhoto: ImageView
    lateinit var character_id: TextView
    lateinit var character_name: TextView
    lateinit var character_status: TextView
    lateinit var character_species: TextView
    lateinit var character_type: TextView
    lateinit var character_gender: TextView


    lateinit var viewModel: DetailActivityVIewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        widget()
        initiate()
    }

    private fun initiate() {
        val id = intent.getStringExtra("id")
        viewModel = ViewModelProvider(this)[DetailActivityVIewModel::class.java]
        if (id != null) {
            observeCharacter(id)
        }
    }

    private fun widget() {
        characterPhoto = findViewById(R.id.characterPhoto)
        character_id = findViewById(R.id.character_id)
        character_name = findViewById(R.id.character_name)
        character_status = findViewById(R.id.character_status)
        character_species = findViewById(R.id.character_species)
        character_type = findViewById(R.id.character_type)
        character_gender = findViewById(R.id.character_gender)
    }

    private fun observeCharacter(id:String){
        viewModel.queryCharacter(id)
        viewModel.character.observe(this) { response ->
            when (response) {
                is ViewState.Loading -> {
                    showToast("Loading Data")
                }
                is ViewState.Success -> {
                    if (response.value?.data?.character == null) {
                        showToast("No Data is Exist!!!")
                    } else {
                        setData(response.value.data?.character)
                    }
                }
                is ViewState.Error -> {
                    showToast("Getting Data Error")
                }
            }
        }
    }

    override fun nextActivity(id:String) {
        TODO("Not yet implemented")
    }

    override fun provideContext(): Context {
        return this
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private fun setData(characterQuery: CharacterQuery.Character?){
        Glide.with(this).load(characterQuery?.image).into(characterPhoto)
        character_id.text="ID: "+characterQuery?.id
        character_name.text="Name: "+characterQuery?.name
        character_status.text="Status: "+characterQuery?.status
        character_species.text="Species: "+characterQuery?.species
        character_type.text="Type: "+characterQuery?.type
        character_gender.text="Gender: "+characterQuery?.gender

    }
}