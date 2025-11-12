package org.example

/**
 * Almacena y gestiona todas las operaciones disponibles.
 * Cumple con SRP (es solo un registro) e implementa OperationFinder (DIP).
 */
object OperationRegistry : OperationFinder {
    private val operations = mutableMapOf<String, Operacion>()

    init {
        // Operaciones básicas
        registrar("+", Suma())
        registrar("-", Resta())
        registrar("*", Multiplicacion())
        registrar("/", Division())

        // Operaciones científicas
        registrar("sen", Seno())
        registrar("cos", Coseno())
        registrar("tan", Tangente())
        registrar("pow", Potencia())
        registrar("raiz", Raiz())

        // Operación de utilería para la GUI
        registrar("NEGATE", Negacion())
    }

    fun registrar(simbolo: String, operacion: Operacion) {
        operations[simbolo] = operacion
    }

    // Implementación de OperationFinder (para DIP)
    override fun obtener(simbolo: String): Operacion? = operations[simbolo]

    fun obtenerOperaciones(): Set<String> = operations.keys
}