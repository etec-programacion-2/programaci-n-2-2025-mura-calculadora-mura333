package org.example
fun main() {
    val registry = OperationRegistry()
    registry.register("+", AdditionOperation())
    registry.register("-", SubtractionOperation())
    registry.register("*", MultiplicationOperation())
    registry.register("/", DivisionOperation())
    val calculadora = Calculadora(registry)
    println("Suma: " + calculadora.calcular("+", listOf(10.0, 5.0, 2.0)))
    println("Resta: " + calculadora.calcular("-", listOf(10.0, 5.0, 2.0)))
    println("Multiplicación: " + calculadora.calcular("*", listOf(2.0, 3.0, 4.0)))
    println("División: " + calculadora.calcular("/", listOf(20.0, 2.0, 2.0)))
}
