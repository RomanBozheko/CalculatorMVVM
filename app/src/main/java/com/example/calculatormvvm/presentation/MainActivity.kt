package com.example.calculatormvvm.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.calculatormvvm.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "TAG_MainActivity-> "
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        listOfButtonListener()
        mine()

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(this)).get(MyViewModel::class.java)

        viewModel.resultOfCalc.observe(this) {
            binding.resString.text = it
        }
        viewModel.resultOfDropEndSymbol.observe(this) {
            binding.mathString.text = it
        }
    }
    private  fun mine() = CoroutineScope(Dispatchers.Default).launch{
        val flow = viewModel.simple()
        flow.collect{value -> println(value)}
    }


    private fun clearText() {
        binding.mathString.text = ""
        binding.resString.text = ""
    }

    private fun setTextFields(str: String) {
        Log.d(TAG, "setTextFields")
        binding.mathString.append(str)
    }

    private fun listOfButtonListener() {
        Log.d(TAG, "listOfButtonListener")
        binding.buttonNine.setOnClickListener() {
            setTextFields(
                binding.buttonNine.text.toString()
            )
        }
        binding.buttonEight.setOnClickListener() {
            setTextFields(
                binding.buttonEight.text.toString()
            )
        }
        binding.buttonSeven.setOnClickListener() {
            setTextFields(
                binding.buttonSeven.text.toString()
            )
        }
        binding.buttonSix.setOnClickListener() {
            setTextFields(
                binding.buttonSix.text.toString()
            )
        }
        binding.buttonFive.setOnClickListener() {
            setTextFields(
                binding.buttonFive.text.toString()
            )
        }
        binding.buttonFour.setOnClickListener() {
            setTextFields(
                binding.buttonFour.text.toString()
            )
        }
        binding.buttonThree.setOnClickListener() {
            setTextFields(
                binding.buttonThree.text.toString()
            )
        }
        binding.buttonTwo.setOnClickListener() {
            setTextFields(
                binding.buttonTwo.text.toString()
            )
        }
        binding.buttonOne.setOnClickListener() {
            setTextFields(
                binding.buttonOne.text.toString()
            )
        }
        binding.buttonZero.setOnClickListener() {
            setTextFields(
                binding.buttonZero.text.toString()
            )
        }
        binding.buttonOpen.setOnClickListener() {
            setTextFields(
                binding.buttonOpen.text.toString()
            )
        }
        binding.buttonClose.setOnClickListener() {
            setTextFields(
                binding.buttonClose.text.toString()
            )
        }
        binding.buttonPoint.setOnClickListener() {
            setTextFields(
                binding.buttonPoint.text.toString()
            )
        }
        binding.buttonDivide.setOnClickListener() {
            setTextFields(
                binding.buttonDivide.text.toString()
            )
        }
        binding.buttonMultiply.setOnClickListener() {
            setTextFields(
                binding.buttonMultiply.text.toString()
            )
        }
        binding.buttonPlus.setOnClickListener() {
            setTextFields(
                binding.buttonPlus.text.toString()
            )
        }
        binding.buttonMinus.setOnClickListener() {
            setTextFields(
                binding.buttonMinus.text.toString()
            )
        }
        binding.buttonClear.setOnClickListener() {
            clearText()
        }
        binding.buttonBack.setOnClickListener() {
            viewModel.dropEndSymbol(binding.mathString.text.toString())
        }

        binding.buttonRavno.setOnClickListener() {
            viewModel.calculate(binding.mathString.text.toString())
        }
    }
}