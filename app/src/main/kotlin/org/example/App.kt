package org.example

fun main() {
    // Instanciar todos los componentes necesarios
    val calc = Calculadora()
    val parser = RpnParser()
    val evaluator = RpnEvaluator()

    println("Calculadora RPN Interactiva")
    println("Comandos: 'quit' o 'exit' para salir, 'ops' para ver operaciones.")
    println("Ejemplo: 3 4 5 + *")

    // Implementar un bucle que lea una línea de la consola
    while (true) {
        print("> ")
        val entrada = readLine()

        if (entrada.isNullOrEmpty()) continue

        // Se usa lowercase() en lugar de toLowerCase() para resolver la deprecación
        val comando = entrada.trim().lowercase()

        // Permitir salir con comando especial
        if (comando == "exit" || comando == "quit") {
            println("Saliendo de la calculadora. ¡Adiós!")
            break
        }

        if (comando == "ops") {
            println("Operaciones disponibles: ${calc.obtenerOperacionesDisponibles().joinToString(", ")}")
            continue
        }

        try {
            // Procesar usando el parser y el evaluador
            val tokens = parser.parse(entrada)
            val resultado = evaluator.evaluate(tokens)

            // Mostrar la explicación si la expresión es compleja (opcional, ayuda al usuario)
            if (tokens.size > 2) {
                println("--- Explicación Paso a Paso ---")
                evaluator.explicarEvaluacion(tokens)
                println("-----------------------------")
            }

            println("= $resultado")

        } catch (e: IllegalArgumentException) {
            // Capturar y mostrar errores de forma amigable
            println("❌ Error de Expresión: ${e.message}")
        } catch (e: Exception) {
            println("❌ Error Inesperado: ${e.message}")
        }
    }
}