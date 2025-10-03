package org.example;

class OperationRegistry {
    private val historial: MutableList<Cuenta> = mutableListOf()

    fun registrarOperacion(operando1: Double, simbolo: String, operando2: Double, resultado: Double) {
        val cuenta = Cuenta(operando1, simbolo, operando2, resultado)
        historial.add(cuenta)
    }

    fun mostrarHistorial() {
        println("\n=== Historial de Operaciones ===")
        println("Operando1 | Operador | Operando2 | Resultado")
        println("----------------------------------------")

        historial.forEachIndexed { index, cuenta ->
            println("${cuenta.operando1}      |    ${cuenta.simbolo}    |    ${cuenta.operando2}      |    ${cuenta.resultado}")
        }
        println("----------------------------------------")
        println("Total de operaciones: ${historial.size}")
    }

    fun obtenerHistorial(): List<Cuenta> {
        return historial.toList()
    }
}