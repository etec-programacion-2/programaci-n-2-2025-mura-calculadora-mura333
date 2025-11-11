package org.example

class Calculadora {
    fun obtenerOperacionesDisponibles(): Set<String> {
        return OperationRegistry.obtenerOperaciones()
    }
}