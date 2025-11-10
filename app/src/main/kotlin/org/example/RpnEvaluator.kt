package org.example

class RpnEvaluator(private val calculadora: Calculadora) {

    fun evaluate(tokens: List<String>): Double {
        val pila = mutableListOf<Double>()

        for (token in tokens) {
            if (token.toDoubleOrNull() != null) {
                pila.add(token.toDouble())
            } else {
                if (pila.size < 2) throw IllegalArgumentException("Expresión mal formada")
                val op2 = pila.removeLast()
                val op1 = pila.removeLast()
                pila.add(when (token) {
                    "+" -> calculadora.suma(op1, op2)
                    "-" -> calculadora.resta(op1, op2)
                    "*" -> calculadora.multiplicacion(op1, op2)
                    "/" -> calculadora.division(op1, op2)
                    "sen" -> calculadora.seno(op1)
                    "cos" -> calculadora.coseno(op1)
                    "tan" -> calculadora.tangente(op1)
                    "pow" -> calculadora.potencia(op1, op2)
                    "raiz" -> calculadora.raiz(op1)
                    else -> throw IllegalArgumentException("Operador desconocido")
                })
            }
        }

        if (pila.size != 1) throw IllegalArgumentException("Expresión mal formada")
        return pila[0]
    }

    // Método para mostrar el paso a paso
    fun explicarEvaluacion(tokens: List<String>) {
        println("Pila: []")
        val pila = mutableListOf<Double>()

        for (token in tokens) {
            if (token.toDoubleOrNull() != null) {
                pila.add(token.toDouble())
                println("→ \"$token\": $pila")
            } else {
                val op2 = pila.removeLast()
                val op1 = pila.removeLast()
                val resultado = when (token) {
                    "+" -> calculadora.suma(op1, op2)
                    "-" -> calculadora.resta(op1, op2)
                    "*" -> calculadora.multiplicacion(op1, op2)
                    "/" -> calculadora.division(op1, op2)
                    "pow" -> calculadora.potencia(op1, op2)
                    else -> throw IllegalArgumentException("Operador desconocido")
                }
                println("→ \"$token\": $pila    ($op1 $token $op2 = $resultado)")
                pila.add(resultado)
            }
        }
        println("Resultado: ${pila[0]}")
    }
}