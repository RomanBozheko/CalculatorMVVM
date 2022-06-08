package com.example.calculatormvvm.domain

import android.util.Log
import com.example.calculatormvvm.MyViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class Calculator: MyCalculator {
    override fun calculate(str: String):String {
        var res: String
        try {
            val calc = ExpressionBuilder(str).build()
            res = calc.evaluate().toLong().toString()
        } catch (e: Exception) {
            res = "ERROR"
            Log.e(MyViewModel.TAG, e.message.toString())
        }
        return res
    }

    override fun dropEndSymbol(str: String): String {
        return str.dropLast(1)
    }
}