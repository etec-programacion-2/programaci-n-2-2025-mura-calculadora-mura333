package org.example;
// Clase Seno
class Seno : Operacion {
    override fun ejecutar(vararg operandos: Double): Double {
        require(operandos.size == 1) { "El seno requiere exactamente un argumento" }
        return kotlin.math.sin(operandos[0])
    }
}


