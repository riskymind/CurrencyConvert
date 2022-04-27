package com.asterisk.currencyconverter.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.asterisk.currencyconverter.R
import com.asterisk.currencyconverter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpBtnClick()

        observeResponse()
    }

    private fun observeResponse() {
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when (event) {
                    MainViewModel.CurrencyEvent.Empty -> {
                        Unit
                    }
                    is MainViewModel.CurrencyEvent.Failed -> {
                        binding.apply {
                            progressBar.isVisible = false
                            tvResult.setTextColor(Color.RED)
                            tvResult.text = event.errorText
                        }
                    }
                    MainViewModel.CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is MainViewModel.CurrencyEvent.Success -> {
                        binding.apply {
                            progressBar.isVisible = false
                            tvResult.setTextColor(Color.BLACK)
                            tvResult.text = event.resultText
                        }
                    }
                }
            }
        }
    }

    private fun setUpBtnClick() {
        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.etFrom.text.toString(),
                binding.spFromCurrency.selectedItem.toString(),
                binding.spToCurrency.selectedItem.toString()
            )
        }
    }


}