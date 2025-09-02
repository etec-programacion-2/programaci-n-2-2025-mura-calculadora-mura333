package org.example;
// Clase Resta
class Resta : Operacion {
    override fun ejecutar(vararg operandos: Double): Double {
        return operandos.fold(operandos[0]) { acc, i -> acc - i }
    }
}