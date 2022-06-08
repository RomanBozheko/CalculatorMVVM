package com.example.calculatormvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MyViewModel() : ViewModel() {

    companion object {
        const val TAG = "TAG_MyViewModel-> "
    }

    init {
        Log.d(TAG, "MyViewModel_created")
    }

    private val resultOfCalcMutable = MutableLiveData<String>()
    val resultOfCalc: LiveData<String> = resultOfCalcMutable

    private val resultOfDropEndSymbolMutable = MutableLiveData<String>()
    val resultOfDropEndSymbol: LiveData<String> = resultOfDropEndSymbolMutable

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "MyViewModel_onCleared")
    }

    fun calculate(stringForCalc: String) {
        Log.d(TAG, "MyViewModel.calculate")
        var res: String
        try {
            val calc = ExpressionBuilder(stringForCalc).build()
            res = calc.evaluate().toLong().toString()
        } catch (e: Exception) {
            res = "ERROR"
            Log.e(TAG, e.message.toString())
        }
        resultOfCalcMutable.value = res
    }

    fun dropEndSymbol(mathStr: String) {
        Log.d(TAG, "MyViewModel.dropEndSymbol")
        val res = mathStr.dropLast(1)
        resultOfDropEndSymbolMutable.value = res
    }


}