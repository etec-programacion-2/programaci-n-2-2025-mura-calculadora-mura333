package org.example

class Cuenta(
    val operando1: Double,
    val simbolo: String,
    val operando2: Double,
    val resultado: Double
) {
    override fun toString(): String {
        return "$operando1 $simbolo $operando2 = $resultado"
    }
}