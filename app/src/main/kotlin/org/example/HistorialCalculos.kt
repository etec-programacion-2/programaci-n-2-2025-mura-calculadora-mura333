package org.example

class HistorialCalculos {
    private val operaciones: MutableList<Operacion> = mutableListOf()


    fun agregarOperacion(operacion: Operacion) {
        operaciones.add(operacion)
    }


    fun obtenerHistorial(): List<Operacion> {
        return operaciones.toList()
    }
}
