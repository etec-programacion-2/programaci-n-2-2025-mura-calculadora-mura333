package org.example

import kotlin.math.pow

class Potencia : Operacion {
    override fun ejecutar(a: Double, b: Double): Double {
        return a.pow(b)
    }
}