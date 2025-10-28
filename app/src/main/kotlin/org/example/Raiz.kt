package org.example

import kotlin.math.sqrt

class Raiz : Operacion {
    override fun ejecutar(a: Double, b: Double): Double {
        require(a >= 0) { "No se puede calcular raíz de número negativo" }
        return sqrt(a)
    }
}