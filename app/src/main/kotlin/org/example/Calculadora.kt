package org.example

import java.util.Stack

/**
 * Motor central RPN y Modelo de Estado (MVC).
 * Gestiona la pila y coordina la ejecución de operaciones usando OperationFinder (DIP).
 * La lógica RPN persistente reside aquí.
 */
class Calculadora(private val operationFinder: OperationFinder) { // Inversión de Dependencia
    // La pila de la calculadora RPN es el estado persistente.
    private val pila: Stack<Double> = Stack()

    fun obtenerPila(): List<Double> = pila.toList()

    fun push(numero: Double) {
        pila.push(numero)
    }

    /**
     * Aplica una operación a los elementos de la pila.
     */
    fun aplicarOperacion(simbolo: String) {
        // --- 1. Manejo de operaciones especiales de pila (SWAP)
        if (simbolo == "SWAP") {
            if (pila.size < 2) {
                throw IllegalStateException("Se necesitan al menos 2 elementos para SWAP.")
            }
            val a = pila.pop()
            val b = pila.pop()
            pila.push(a)
            pila.push(b)
            return
        }

        // --- 2. Manejo de operaciones registradas
        // Usa la abstracción OperationFinder.
        val operacion = operationFinder.obtener(simbolo)
            ?: throw IllegalArgumentException("Operador '$simbolo' no soportado.")

        val numOperandos = operacion.numOperandos

        if (pila.size < numOperandos) {
            throw IllegalStateException("Faltan operandos en la pila para la operación '$simbolo'.")
        }

        val operandosParaOperacion = mutableListOf<Double>()
        // Extraer N operandos de la pila en orden [op1, op2, ...]
        for (i in 1..numOperandos) {
            operandosParaOperacion.add(0, pila.pop())
        }

        // Ejecutar la operación. OCP: la lógica de la Calculadora no cambia, solo se extiende el Operacion.
        val resultado = operacion.ejecutar(operandosParaOperacion)

        pila.push(resultado)
    }

    fun limpiarPila() {
        pila.clear()
    }
}