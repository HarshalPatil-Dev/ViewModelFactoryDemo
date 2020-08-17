package com.sample.viewmodelfactorydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sample.viewmodelfactorydemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModelFactory = MainActivityViewModelFactory(100)  //Providing initial value thrown ViewModel Constructor.
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainActivityViewModel::class.java)

        binding.resultTextView.text = viewModel.getTotal().toString()

        binding.addButton.setOnClickListener {
            if (binding.inputEditText.text.isNotEmpty()) {
                viewModel.setTotal(binding.inputEditText.text.toString().toInt())
                binding.resultTextView.text = viewModel.getTotal().toString()
            } else {
                Toast.makeText(this, "Please insert Value", Toast.LENGTH_SHORT).show()
            }
        }

    }
}