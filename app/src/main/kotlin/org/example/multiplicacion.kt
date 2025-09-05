package org.example
class Multiplicacion : Operacion {
    override fun ejecutar(vararg operandos: Double): Double {
        return operandos.fold(1.0) { acc, i -> acc * i }
    }
}
