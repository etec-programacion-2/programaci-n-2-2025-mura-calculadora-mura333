package org.example
class Calculadora {
    private var operacion: Operacion? = null

    fun setOperacion(op: Operacion) {
        operacion = op
    }

    fun getOperacion(): Operacion? {
        return operacion
    }

    fun calcular(a: Double, b: Double): Double? {
        return operacion?.ejecutar(a, b)
    }
}
