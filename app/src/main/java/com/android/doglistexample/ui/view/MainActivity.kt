package com.android.doglistexample.ui.view

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.doglistexample.R
import com.android.doglistexample.databinding.ActivityMainBinding
import com.android.doglistexample.ui.adapter.DogListAdapter
import com.android.doglistexample.ui.viewmodel.DogViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{

    private lateinit var binding: ActivityMainBinding
    private val dogViewModel: DogViewModel by viewModels()
    private lateinit var adapter: DogListAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(2000)
        setTheme(R.style.DogListExample)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loading.isVisible = false
        binding.searchView.setOnQueryTextListener(this)
        callLoading()
        showListAdapter()
        initRecycler()
        showToast()
    }

    private fun initRecycler() {
        adapter = DogListAdapter(dogImages)
        binding.rvDogList.setHasFixedSize(true)
        binding.rvDogList.layoutManager = LinearLayoutManager(this)
        binding.rvDogList.adapter = adapter
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            dogViewModel.getData(query.lowercase(Locale.getDefault()))
        }
        hideKeyboard()
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun showListAdapter() {
        dogViewModel.dogModel.observe(this, {
            dogImages.clear()
            dogImages.addAll(it.images)
            adapter.notifyDataSetChanged()
        })
    }

    private fun callLoading() {
        dogViewModel.isLoading.observe(this, {
          binding.loading.isVisible = it
        })
    }

    private fun showToast(){
        dogViewModel.showToast.observe(this, {
            Toast.makeText(this, getString(it), Toast.LENGTH_SHORT).show()
        })
    }

}