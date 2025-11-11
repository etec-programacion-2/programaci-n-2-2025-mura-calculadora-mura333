package org.example

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.event.ActionEvent

/**
 * Clase controladora para la interfaz de la calculadora RPN.
 * Esta clase maneja todos los eventos de la interfaz y contiene la lógica de interacción.
 */
class CalculatorController {

    // @FXML inyecta el TextField definido en CalculatorUI.fxml (fx:id="displayTextField")
    @FXML
    private lateinit var displayTextField: TextField

    // String que almacena la expresión RPN actual para ser evaluada
    private var currentExpression: String = ""

    // Componentes del Core (Se inicializan aquí, en una aplicación real usaríamos inyección de dependencia)
    private val evaluator = RpnEvaluator()
    private val parser = RpnParser()

    // --- Métodos de Evento (Conectados con onAction en el FXML) ---

    // Maneja la pulsación de los botones numéricos (0-9)
    @FXML
    private fun handleNumber(event: ActionEvent) {
        val button = event.source as Button
        // Simplemente añadimos el número al final de la expresión
        currentExpression += button.text
        updateDisplay()
    }

    // Maneja el botón decimal
    @FXML
    private fun handleDecimal() {
        // Lógica simple: asegurar que no haya ya un punto en el último número
        if (currentExpression.isEmpty() || currentExpression.endsWith(" ")) {
            currentExpression += "0."
        } else if (!currentExpression.split(" ").last().contains(".")) {
            currentExpression += "."
        }
        updateDisplay()
    }

    // Maneja la tecla 'Enter' o espacio, separando el número actual
    @FXML
    private fun handleEnter() {
        if (currentExpression.isNotEmpty() && !currentExpression.endsWith(" ")) {
            currentExpression += " " // Añade un espacio para separar el número de la operación siguiente
        }
        updateDisplay()
    }

    // Maneja los botones de operación (+, *, sen, pow, etc.)
    @FXML
    private fun handleOperation(event: ActionEvent) {
        val button = event.source as Button
        val operator = button.text

        // Primero, aseguramos un espacio si hay un número previo
        if (currentExpression.isNotEmpty() && !currentExpression.endsWith(" ")) {
            currentExpression += " "
        }
        // Luego, añadimos el operador, seguido de un espacio (para RPN)
        currentExpression += "$operator "
        updateDisplay()
    }

    // Botón de Borrar Último Carácter (DEL)
    @FXML
    private fun handleDelete() {
        if (currentExpression.isNotEmpty()) {
            currentExpression = currentExpression.substring(0, currentExpression.length - 1)
        }
        updateDisplay()
    }

    // Botón de Limpiar todo (CLR)
    @FXML
    private fun handleClear() {
        currentExpression = ""
        displayTextField.text = ""
    }

    // Placeholder: El botón 'Eval' (Evaluación final)
    @FXML
    private fun handleEvaluate() {
        try {
            // 1. Limpiamos la expresión de espacios redundantes
            val inputToParse = currentExpression.trim()

            if (inputToParse.isEmpty()) {
                displayTextField.text = "Error: Expresión vacía"
                return
            }

            // 2. Tokenizar y evaluar
            val tokens = parser.parse(inputToParse)
            val result = evaluator.evaluate(tokens)

            // 3. Mostrar el resultado y reiniciar la expresión para la próxima operación
            displayTextField.text = result.toString()
            currentExpression = result.toString() // El resultado se convierte en el primer operando de la siguiente expresión

        } catch (e: Exception) {
            // Manejo de errores de RPN (división por cero, expresión mal formada, etc.)
            displayTextField.text = "Error: ${e.message}"
            currentExpression = "" // Reseteamos después de un error grave
        }
    }

    // Placeholder: Botón de Intercambio (Swap) para RPN
    @FXML
    private fun handleSwap() {
        // Implementación futura: Intercambiar los dos últimos elementos en la pila
        displayTextField.text = "Función SWAP (Pendiente)"
    }

    // Método privado para actualizar el TextField
    private fun updateDisplay() {
        // Muestra la expresión RPN actual en la pantalla
        displayTextField.text = currentExpression.trim()
    }
}