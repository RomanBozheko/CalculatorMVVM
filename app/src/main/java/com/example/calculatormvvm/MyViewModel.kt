package com.example.calculatormvvm

import android.util.Log
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

    val resultOfCalc = MutableLiveData<String>()
    val resultOfDropEndSymbol = MutableLiveData<String>()

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
            res = "ERROR_MESSAGE"
            Log.e(TAG, e.message.toString())
        }
        resultOfCalc.value = res
    }

    fun dropEndSymbol(mathStr: String) {
        Log.d(TAG, "MyViewModel.dropEndSymbol")
        val res = mathStr.dropLast(1)
        resultOfDropEndSymbol.value = res
    }
}