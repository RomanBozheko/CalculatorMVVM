package com.example.calculatormvvm.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculatormvvm.domain.Calculator
import kotlinx.coroutines.*


class MyViewModel() : ViewModel() {

    companion object {
        const val TAG = "TAG_MyViewModel-> "
    }

    init {
        Log.d(TAG, "MyViewModel_created")
    }

    private val calculator = Calculator()

    private val resultOfCalcMutable =
        MutableLiveData<String>() //kotlin unit // корутины (супервайзер, скоуп)

    val resultOfCalc: LiveData<String> = resultOfCalcMutable

    private val resultOfDropEndSymbolMutable = MutableLiveData<String>()
    val resultOfDropEndSymbol: LiveData<String> = resultOfDropEndSymbolMutable

    var job: Job? = null


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "MyViewModel_onCleared")


    }

    fun calculate(stringForCalc: String)/* = CoroutineScope(Dispatchers.Default).launch()*/ {
        Log.d(TAG, "MyViewModel.calculate")

        job = CoroutineScope(Dispatchers.Default).launch {

//            resultOfCalcMutable.postValue(calculator.calculate(stringForCalc))
//            job?.cancel()

            val result = calculator.calculate(stringForCalc)


            withContext(Dispatchers.Main) {
                if (result.isNotEmpty()) {
                    resultOfCalcMutable.value = result
                } else {
                    Log.e(TAG, "MyViewModel.calculate.Error")
                }
                job?.cancel()
            }

        }
    }
//    fun calculate(stringForCalc: String) {
//        Log.d(TAG, "MyViewModel.calculate")
//        resultOfCalcMutable.value = calculator.calculate(stringForCalc)
//    }

    fun dropEndSymbol(mathStr: String) {
        Log.d(TAG, "MyViewModel.dropEndSymbol")
        resultOfDropEndSymbolMutable.value = calculator.dropEndSymbol(mathStr)
    }


}