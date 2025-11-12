package org.example

// Constructor con un solo argumento: Calculadora
class RpnEvaluator(private val calculadora: Calculadora) {

    fun evaluate(tokens: List<String>): Double {
        val pila = mutableListOf<Double>()

        for (token in tokens) {
            if (token.toDoubleOrNull() != null) {
                pila.add(token.toDouble())
            } else {
                // El Evaluador usa el OperationRegistry directamente para encontrar la Operación.
                val operacion = OperationRegistry.obtener(token)
                    ?: throw IllegalArgumentException("Operador '$token' no soportado")

                val numOperandos = operacion.numOperandos

                if (pila.size < numOperandos) {
                    throw IllegalArgumentException("Expresión mal formada")
                }

                val operandosParaOperacion = mutableListOf<Double>()
                // Extraer N operandos de la pila
                for (i in 1..numOperandos) {
                    operandosParaOperacion.add(0, pila.removeLast())
                }

                pila.add(operacion.ejecutar(operandosParaOperacion))
            }
        }

        if (pila.size != 1) throw IllegalArgumentException("Expresión mal formada")
        return pila[0]
    }
}