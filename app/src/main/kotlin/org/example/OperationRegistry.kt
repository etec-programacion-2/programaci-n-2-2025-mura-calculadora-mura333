package org.example;
object OperationRegistry {
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
    }

    fun registrar(simbolo: String, operacion: Operacion) {
        operations[simbolo] = operacion
    }

    fun obtener(simbolo: String): Operacion? = operations[simbolo]

    fun obtenerOperaciones(): Set<String> = operations.keys
}