package com.example.calculatormvvm.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculatormvvm.domain.Calculator
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext


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

    private val coroutineScope = CoroutineScope(/*Job() + */Dispatchers.Default)

//    private lateinit var job:Job


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "MyViewModel_onCleared")

        coroutineScope.cancel()
        Log.d(TAG, "MyViewModel_coroutineScope_cancel")


    }

    fun calculate(stringForCalc: String) /* = CoroutineScope(Dispatchers.Default).launch */ {
        Log.d(TAG, "MyViewModel.calculate")
        /**
         * CoroutineContext - Dispatchers, id, name, Exception
         **/


//        job.launch{}

//        job = CoroutineScope(Dispatchers.Default).launch {

//        job = CoroutineScope(SupervisorJob() + Dispatchers.Default).launch {}

//        job = CoroutineScope(SupervisorJob()).launch {

        coroutineScope.launch {

//
//            while (isActive) {

//            }
//            ensureActive() ????   yield()

//            resultOfCalcMutable.postValue(calculator.calculate(stringForCalc))

            val result = calculator.calculate(stringForCalc)
            Log.d(TAG, "Thread_Name >>> ${Thread.currentThread().name}" + " || result = $result")

            withContext(Dispatchers.Main) {


                if (result.isNotEmpty()) {
                    resultOfCalcMutable.value = result
                    Log.d(TAG, "Thread_Name >>> ${Thread.currentThread().name}")
                } else {
                    throw IllegalArgumentException("Err_")
                }
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