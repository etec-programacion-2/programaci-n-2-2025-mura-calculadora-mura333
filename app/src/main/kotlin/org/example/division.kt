package org.example

class Division : Operacion {
    override fun ejecutar(vararg operandos: Double): Double {
        require(operandos.size == 2) { "La división requiere exactamente dos operandos" }
        require(operandos[1] != 0.0) { "No se puede dividir por cero" }
        return operandos[0] / operandos[1]
    }
}