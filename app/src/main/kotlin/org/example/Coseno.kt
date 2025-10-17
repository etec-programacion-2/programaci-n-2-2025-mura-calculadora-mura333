package org.example
import kotlin.math.cos
import kotlin.math.PI

class Coseno : Operacion {
    override fun ejecutar(a: Double, b: Double): Double {
        val radianes = a * PI / 180.0
        return cos(radianes)
    }
}