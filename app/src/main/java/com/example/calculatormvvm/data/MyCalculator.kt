package com.example.calculatormvvm.data

interface MyCalculator {
    fun calculate(str: String):String
    fun dropEndSymbol(str: String):String
}