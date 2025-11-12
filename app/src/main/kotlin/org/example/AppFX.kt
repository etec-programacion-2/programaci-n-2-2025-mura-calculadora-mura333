package org.example

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import java.io.IOException

class AppFX : Application() {

    @Throws(IOException::class)
    override fun start(stage: Stage) {
        // Obtiene la URL del FXML desde la raíz de los recursos (src/main/resources/).
        // NOTA: El '/' inicial es crucial para buscar desde la raíz del classpath.
        val fxmlUrl = AppFX::class.java.getResource("/calculatorUI.fxml")

        if (fxmlUrl == null) {
            println("Error: No se encontró calculatorUI.fxml.")
            // Mensaje de error ajustado para indicar la ubicación esperada.
            throw IOException("El recurso FXML (calculatorUI.fxml) no fue encontrado. Verifique que esté en la ruta de recursos: /src/main/resources/.")
        }

        val fxmlLoader = FXMLLoader(fxmlUrl)

        // El elemento raíz del FXML es AnchorPane.
        val root: AnchorPane = fxmlLoader.load()

        // Altura ajustada para el nuevo diseño de botones.
        val scene = Scene(root, 300.0, 480.0)

        // Cargar el CSS (styles.css) desde la raíz de los recursos.
        val cssResource = AppFX::class.java.getResource("/styles.css")
        if (cssResource != null) {
            scene.stylesheets.add(cssResource.toExternalForm())
        } else {
            println("Advertencia: No se encontró styles.css.")
        }

        stage.title = "RPN Calculator"
        stage.scene = scene
        stage.show()
    }
}