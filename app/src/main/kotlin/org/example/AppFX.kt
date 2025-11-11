package org.example

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.system.exitProcess

/**
 * Clase principal de JavaFX.
 * Se encarga de inicializar y mostrar la ventana principal, cargando el FXML.
 * NOTA: Utiliza VBox y Scene, sin necesidad de Canvas para este layout.
 */
class AppFX : Application() {

    override fun start(primaryStage: Stage) {
        try {
            // 1. Obtener la URL del archivo FXML.
            // Busca 'CalculatorUI.fxml' en la raíz del classpath (src/main/resources).
            val fxmlResource = javaClass.getResource("/CalculatorUI.fxml")

            if (fxmlResource == null) {
                // Mensaje de error si el archivo no se encuentra.
                throw IllegalStateException("❌ FXML no encontrado. Asegúrese de que 'CalculatorUI.fxml' esté en la carpeta src/main/resources/ (raíz).")
            }

            // 2. Cargar el FXML usando la URL.
            val fxmlLoader = FXMLLoader(fxmlResource)

            // El nodo raíz cargado es el VBox definido en CalculatorUI.fxml
            val root = fxmlLoader.load<javafx.scene.layout.VBox>()

            // 3. Crear la escena
            val scene = Scene(root)

            // 4. Configurar el Stage (Ventana)
            primaryStage.title = "Calculadora RPN Científica"
            primaryStage.scene = scene

            // Ajustes finales
            primaryStage.sizeToScene()
            primaryStage.isResizable = false

            // 5. Mostrar la ventana.
            primaryStage.show()

        } catch (e: IllegalStateException) {
            // Manejar errores de recursos no encontrados
            println("❌ ERROR DE RECURSO: ${e.message}")
            exitProcess(1)
        } catch (e: Exception) {
            // Manejo de otros errores (por ejemplo, errores en FXML o en el Controller)
            println("❌ Error crítico al iniciar la aplicación JavaFX:")
            e.printStackTrace()
            exitProcess(1)
        }
    }
}

// Función main que lanza la aplicación
fun main() {
    Application.launch(AppFX::class.java)
}