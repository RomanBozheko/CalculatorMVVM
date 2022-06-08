package com.example.calculatormvvm.domain

interface MyCalculator {
    fun calculate(str: String):String
    fun dropEndSymbol(str: String):String
}