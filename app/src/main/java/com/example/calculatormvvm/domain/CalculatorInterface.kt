package com.example.calculatormvvm.domain

interface CalculatorInterface {
    suspend fun calculate(str: String):String
     fun dropEndSymbol(str: String):String
}