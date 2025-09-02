package org.example;
// Clase Suma
class Suma : Operacion {
    override fun ejecutar(vararg operandos: Double): Double {
        return operandos.sum()
    }
}