package org.example

fun main() {
    val calc = Calculadora()
    val evaluator = RpnEvaluator(calc)
    val parser = RpnParser()

    // Probar una expresión con explicación
    val expresion = "3 4 5 + *"
    println("Evaluando: $expresion")
    evaluator.explicarEvaluacion(parser.parse(expresion))
    println()

    // Otras expresiones normales
    val expresiones = listOf("5 3 +", "10 4 -", "6 7 *", "15 3 /")
    expresiones.forEach { expr ->
        try { println("$expr = ${evaluator.evaluate(parser.parse(expr))}") }
        catch (e: Exception) { println("$expr -> Error: ${e.message}") }
    }
}