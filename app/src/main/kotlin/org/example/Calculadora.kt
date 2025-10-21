package org.example

class Calculadora {
    private val parser = RpnParser()
    private val pila = mutableListOf<Double>()

    fun calcular(expresion: String): Double {
        pila.clear()
        val tokens = parser.parse(expresion)

        for (token in tokens) {
            when {
                esNumero(token) -> {
                    pila.add(token.toDouble())
                }
                OperationRegistry.obtener(token) != null -> {
                    ejecutarOperacion(token)
                }
                else -> {
                    throw IllegalArgumentException("Token no válido: '$token'")
                }
            }
        }

        if (pila.size != 1) {
            throw IllegalArgumentException("Expresión incompleta o inválida. Pila final: $pila")
        }

        return pila.removeAt(pila.size - 1)
    }

    private fun esNumero(token: String): Boolean {
        return try {
            token.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    private fun ejecutarOperacion(operador: String) {
        val operacion = OperationRegistry.obtener(operador)
            ?: throw IllegalArgumentException("Operación no soportada: $operador")

        if (pila.size < operacion.numeroOperandos) {
            throw IllegalArgumentException(
                "Operación $operador requiere ${operacion.numeroOperandos} operandos, " +
                        "pero hay ${pila.size} en la pila"
            )
        }

        val operandos = mutableListOf<Double>()
        for (i in 0 until operacion.numeroOperandos) {
            operandos.add(0, pila.removeAt(pila.size - 1))
        }

        val resultado = operacion.ejecutar(operandos)
        pila.add(resultado)
    }

    fun obtenerOperacionesDisponibles(): Set<String> {
        return OperationRegistry.obtenerOperaciones()
    }
}