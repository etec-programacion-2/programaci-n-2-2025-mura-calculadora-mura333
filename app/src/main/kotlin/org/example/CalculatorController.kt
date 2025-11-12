package org.example

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.event.ActionEvent
import javafx.scene.input.KeyEvent
import javafx.scene.input.KeyCode
import javafx.application.Platform
import java.util.Locale

/**
 * Clase controladora para la interfaz de la calculadora RPN.
 * Gestiona la interacción del usuario y coordina la lógica RPN.
 */
class CalculatorController {

    @FXML
    lateinit var displayTextField: TextField

    // La expresión RPN actual que se está construyendo (ej: "1 2 + 3 *")
    private var currentExpression: String = ""
    // Indica si el display muestra un resultado final o un error (para reiniciar la entrada)
    private var isResultDisplay = false

    // Inicialización de la lógica RPN
    private val calculadora = Calculadora(OperationRegistry)
    private val parser = RpnParser()
    private val evaluator = RpnEvaluator(calculadora)

    @FXML
    fun initialize() {
        // Crucial: Asegura que el foco inicial esté en el TextField para la entrada por teclado.
        Platform.runLater {
            displayTextField.requestFocus()

            // Listener para mantener el foco en el TextField
            displayTextField.focusedProperty().addListener { _, _, isNowFocused ->
                if (!isNowFocused) {
                    displayTextField.requestFocus()
                }
            }
        }
    }

    // ----------------------------------------------------
    // --- Lógica de Manejo de Entrada (Números, Decimales y Espacio)
    // ----------------------------------------------------

    /**
     * Agrega un dígito a la expresión actual.
     */
    private fun handleNumberInput(digit: String) {
        if (isResultDisplay) {
            currentExpression = ""
            isResultDisplay = false
        }

        // Si el último token fue un operador o función, insertamos un espacio para el número.
        val lastToken = currentExpression.substringAfterLast(' ', "")
        if (OperationRegistry.obtener(lastToken) != null) {
            currentExpression += " "
        }

        currentExpression += digit
        updateDisplay()
        displayTextField.positionCaret(currentExpression.length)
    }

    /**
     * Agrega un punto decimal.
     */
    @FXML
    fun handleDecimal() {
        if (isResultDisplay) handleClear()

        val lastToken = currentExpression.substringAfterLast(' ', "")

        // Evita múltiples puntos en el mismo número
        if (lastToken.contains(".")) {
            return
        }

        if (lastToken.isEmpty() || currentExpression.endsWith(' ')) {
            currentExpression += "0."
        } else {
            currentExpression += "."
        }
        updateDisplay()
        displayTextField.positionCaret(currentExpression.length)
    }

    /**
     * MÉTODO VINCULADO AL BOTÓN "Esp". Agrega un espacio para separar tokens RPN.
     */
    @FXML
    fun handleSpace() {
        if (isResultDisplay) {
            isResultDisplay = false
        }
        // Solo agrega espacio si no está vacío y el último caracter no es ya un espacio.
        if (currentExpression.isNotEmpty() && currentExpression.last() != ' ') {
            currentExpression += " "
        }
        updateDisplay()
        displayTextField.positionCaret(currentExpression.length)
    }

    // ----------------------------------------------------
    // --- MANEJO DE TECLADO FÍSICO
    // ----------------------------------------------------

    @FXML
    fun handleKeyPress(event: KeyEvent) {
        val key = event.code
        val text = event.text.lowercase()
        var consumed = true

        when {
            key == KeyCode.SPACE -> handleSpace()
            key == KeyCode.ENTER -> handleEvaluate()
            key == KeyCode.BACK_SPACE -> handleBackspace()
            text.matches(Regex("[0-9]")) -> handleNumberInput(text)
            text == "." || text == "," -> handleDecimal() // Acepta punto o coma

            // Mapeo de operaciones comunes por teclado
            text == "+" -> handleOperationInput("+")
            text == "-" -> handleOperationInput("-")
            text == "*" -> handleOperationInput("*")
            text == "/" -> handleOperationInput("/")

            // Mapeo para funciones trigonométricas y potencia
            // El usuario puede teclear "s", "c", "t" o "^" para las operaciones completas.
            text == "s" -> handleOperationInput("sin")
            text == "c" -> handleOperationInput("cos")
            text == "t" -> handleOperationInput("tan")
            text == "^" -> handleOperationInput("pow")

            else -> consumed = false
        }

        if (consumed) {
            event.consume()
        }
    }

    private fun handleBackspace() {
        if (isResultDisplay) {
            handleClear()
            return
        }
        if (currentExpression.isNotEmpty()) {
            currentExpression = currentExpression.dropLast(1).trimEnd()
            updateDisplay()
            displayTextField.positionCaret(currentExpression.length)
        }
    }


    // ----------------------------------------------------
    // --- MANEJO DE BOTONES (Eventos FXML)
    // ----------------------------------------------------

    @FXML
    private fun handleNumber(event: ActionEvent) {
        val button = event.source as Button
        handleNumberInput(button.text)
    }

    @FXML
    fun handleOperation(event: ActionEvent) {
        val button = event.source as Button
        handleOperationInput(button.text)
    }

    private fun handleOperationInput(symbol: String) {
        if (isResultDisplay) {
            isResultDisplay = false
        }

        if (currentExpression.isNotEmpty() && currentExpression.last() != ' ') {
            currentExpression += " "
        }
        currentExpression += symbol
        updateDisplay()
        displayTextField.positionCaret(currentExpression.length)
    }

    @FXML
    fun handleClear() {
        currentExpression = ""
        isResultDisplay = false
        updateDisplay()
    }

    @FXML
    fun handleNegate() {
        if (isResultDisplay) handleClear()

        val tokens = currentExpression.split(' ').filter { it.isNotEmpty() }.toMutableList()
        if (tokens.isNotEmpty()) {
            val number = tokens.last().toDoubleOrNull()
            if (number != null) {
                tokens[tokens.lastIndex] = (-number).toString()
                currentExpression = tokens.joinToString(" ")
                updateDisplay()
                displayTextField.positionCaret(currentExpression.length)
            }
        }
    }

    @FXML
    fun handleSwap() {
        if (isResultDisplay) handleClear()

        val tokens = currentExpression.split(' ').filter { it.isNotEmpty() }.toMutableList()
        if (tokens.size >= 2) {
            val temp = tokens[tokens.lastIndex]
            tokens[tokens.lastIndex] = tokens[tokens.lastIndex - 1]
            tokens[tokens.lastIndex - 1] = temp
            currentExpression = tokens.joinToString(" ")
            updateDisplay()
            displayTextField.positionCaret(currentExpression.length)
        }
    }

    @FXML
    fun handleEnter() {
        handleEvaluate()
    }

    // ----------------------------------------------------
    // --- Lógica de Cálculo y Vista (CON FORMATO DE RESULTADO FINAL)
    // ----------------------------------------------------

    @FXML
    private fun handleEvaluate() {
        try {
            val inputToParse = currentExpression.trim()
            if (inputToParse.isEmpty()) return

            val tokens = parser.parse(inputToParse)
            if (tokens.isEmpty()) {
                updateDisplay()
                return
            }

            val result = evaluator.evaluate(tokens)

            // Usamos Locale.US para forzar el punto decimal en el String.format
            val resultString = String.format(Locale.US, "%.10f", result)
                .trimEnd('0')
                .trimEnd('.') // Esto soluciona el problema de la "," o "." al final
                .let {
                    // Maneja el caso de -0.0
                    if (it == "-0") "0" else it
                }

            displayTextField.text = resultString
            currentExpression = resultString
            isResultDisplay = true

        } catch (e: Exception) {
            val errorMessage = when(e.message) {
                "Expresión mal formada" -> "Error: Exp. inválida"
                "No se puede dividir por cero" -> "Error: Div/0"
                "No se puede calcular raíz de número negativo" -> "Error: Raíz negativa"
                "Tangente indefinida para este ángulo" -> "Error: Tan indefinida"
                else -> "Error: ${e.message ?: "Desconocido"}"
            }
            displayTextField.text = errorMessage
            currentExpression = ""
            isResultDisplay = true
        }
    }

    private fun updateDisplay() {
        val displayValue = if (currentExpression.isEmpty()) "0" else currentExpression

        displayTextField.text = displayValue

        Platform.runLater {
            displayTextField.requestFocus()
            displayTextField.positionCaret(displayValue.length)
        }
    }
}