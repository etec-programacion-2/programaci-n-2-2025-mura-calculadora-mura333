package org.example

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage

// La clase principal de JavaFX debe heredar de Application
class AppFX : Application() {

    override fun start(primaryStage: Stage) {
        // 1. Crear el nodo raíz (layout)
        val root = StackPane()

        // 2. Crear la escena
        val scene = Scene(root, 400.0, 300.0) // ancho, alto

        // 3. Configurar el Stage (Ventana)
        primaryStage.title = "Calculadora RPN"
        primaryStage.scene = scene

        // 4. Mostrar la ventana
        primaryStage.show()
    }
}

// Función principal que lanza la aplicación (aunque el plugin Gradle ya lo maneja)
fun main() {
    Application.launch(AppFX::class.java)
}