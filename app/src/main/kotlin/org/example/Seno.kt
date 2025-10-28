package org.example
import kotlin.math.sin
import kotlin.math.PI

class Seno : Operacion {
    override fun ejecutar(a: Double, b: Double): Double {
        val radianes = a * PI / 180.0
        return sin(radianes)
    }
}