package com.lucasvidalz.calculadoradeimccompose.calculo

import java.text.DecimalFormat

class CalcularIMC {
    private var resutaldoIMC = ""
    fun calcularIMC(peso: String, altura: String){
        val pesoConvertido = peso.toDouble()
        val alturaConvertida = altura.toDouble()
        val resultado: String

        val imc = pesoConvertido / (alturaConvertida * alturaConvertida)
        val decimalFormat = DecimalFormat("0.00")

        if(imc < 18.5){
            resultado = "Abaixo do peso \nIMC: ${decimalFormat.format(imc)}"
        }else if(imc <= 24.9){
            resultado = "Peso normal \nIMC: ${decimalFormat.format(imc)}"
        }else if(imc <= 29.9){
            resultado = "Sobrepeso \nIMC: ${decimalFormat.format(imc)}"
        }else if(imc <= 34.9){
            resultado = "Obesidade (Grau 1) \nIMC: ${decimalFormat.format(imc)}"
        }else if(imc <= 39.9){
            resultado = "Obesidade Severa (Grau 2) \nIMC: ${decimalFormat.format(imc)}"
        }else{
            resultado = "Obesidade Mórbida (Grau 3) \nIMC: ${decimalFormat.format(imc)}"
        }
        resutaldoIMC = resultado
    }
    fun resultadoIMC():String{
        return resutaldoIMC
    }
}