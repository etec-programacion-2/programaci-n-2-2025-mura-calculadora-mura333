package org.example

import kotlin.math.tan
import kotlin.math.PI
import kotlin.math.cos

class Tangente : Operacion {
    override fun ejecutar(a: Double, b: Double): Double {
        val radianes = a * PI / 180.0
        require(cos(radianes) != 0.0) { "Tangente indefinida para este ángulo" }
        return tan(radianes)
    }
}