package org.example


class Division : Operacion {
    override fun ejecutar(a: Double, b: Double): Double {
        require(b != 0.0) { "No se puede dividir por cero" }
        return a / b
    }
}
