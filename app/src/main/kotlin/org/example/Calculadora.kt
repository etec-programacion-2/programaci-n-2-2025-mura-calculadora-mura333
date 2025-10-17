package org.example
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

        // Para operaciones con un solo operando
        if (operandos.size == 1) {
            return operacion.ejecutar(operandos[0], 0.0)
        }
        // Para operaciones con dos operandos
        else if (operandos.size == 2) {
            return operacion.ejecutar(operandos[0], operandos[1])
        }
        else {
            throw IllegalArgumentException("Demasiados operandos")
        }
    }

    fun obtenerOperacionesDisponibles(): Set<String> {
        return OperationRegistry.obtenerOperaciones()
    }
}