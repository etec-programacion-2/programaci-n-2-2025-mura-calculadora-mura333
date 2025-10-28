package org.example

fun main() {
    val calc = Calculadora()
    val operaciones = listOf("5 3 +", "10 4 -", "6 7 *", "15 3 /",
        "30 sen", "60 cos", "45 tan", "2 3 pow", "16 raiz",
        "3 4 5 * +", "7 2 3 * -", "4 2 /")

    operaciones.forEach { try { println("$it = ${calc.calcular(it)}") }
    catch (e: Exception) { println("$it -> ${e.message}") } }
}