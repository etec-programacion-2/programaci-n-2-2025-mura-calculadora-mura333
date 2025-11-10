package org.example
import kotlin.math.pow

class Calculadora {
    fun calcular(expresion: String): Double {
        val partes = expresion.split(" ").filter { it.isNotBlank() }

        if (partes.size < 2) {
            throw IllegalArgumentException("Expresión debe tener al menos un número y un operador")
        }

        val operador = partes.last()
        val operandos = partes.dropLast(1).map { it.toDouble() }

        val operacion = OperationRegistry.obtener(operador)
            ?: throw IllegalArgumentException("Operación '$operador' no soportada")

        return operacion.ejecutar(operandos)
    }

    // Métodos para RpnEvaluator
    fun suma(a: Double, b: Double) = a + b
    fun resta(a: Double, b: Double) = a - b
    fun multiplicacion(a: Double, b: Double) = a * b
    fun division(a: Double, b: Double): Double {
        if (b == 0.0) throw IllegalArgumentException("No se puede dividir por cero")
        return a / b
    }
    fun seno(a: Double) = kotlin.math.sin(a * kotlin.math.PI / 180.0)
    fun coseno(a: Double) = kotlin.math.cos(a * kotlin.math.PI / 180.0)
    fun tangente(a: Double) = kotlin.math.tan(a * kotlin.math.PI / 180.0)
    fun potencia(a: Double, b: Double) = a.pow(b)
    fun raiz(a: Double) = kotlin.math.sqrt(a)

    fun obtenerOperacionesDisponibles(): Set<String> {
        return OperationRegistry.obtenerOperaciones()
    }
}