package org.example
fun main() {
    val calc = Calculadora()

    val operaciones = listOf(
        "5 3 +", "10 4 -", "6 7 *", "15 3 /",
        "30 sen", "60 cos", "45 tan", "2 3 pow", "16 raiz"
    )

    operaciones.forEach { op ->
        try {
            println("$op = ${calc.calcular(op)}")
        } catch (e: Exception) {
            println("$op -> Error: ${e.message}")
        }
    }
}
