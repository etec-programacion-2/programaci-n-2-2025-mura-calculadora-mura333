package org.example

class Division : Operacion {
    override fun ejecutar(a: Double, b: Double): Double {
        if (b == 0.0) throw ArithmeticException("División por cero")
        return a / b
    }
}
