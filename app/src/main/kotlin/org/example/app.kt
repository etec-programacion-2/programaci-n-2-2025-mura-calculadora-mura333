package org.example

fun main() {
    val registry = OperationRegistry()

    // Ejemplo de uso
    registry.registrarOperacion(5.0, "+", 3.0, 8.0)
    registry.registrarOperacion(10.0, "-", 4.0, 6.0)
    registry.registrarOperacion(6.0, "*", 7.0, 42.0)

    registry.mostrarHistorial()
}