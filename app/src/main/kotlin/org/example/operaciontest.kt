package org.example

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

class OperacionTest {
    @Test
    fun testSuma() {
        val suma = Suma()
        assertEquals(6.0, suma.ejecutar(1.0, 2.0, 3.0), 0.01)
    }

    @Test
    fun testResta() {
        val resta = Resta()
        assertEquals(5.0, resta.ejecutar(10.0, 3.0, 2.0), 0.01)
    }

    @Test
    fun testMultiplicacion() {
        val multiplicacion = Multiplicacion()
        assertEquals(24.0, multiplicacion.ejecutar(2.0, 3.0, 4.0), 0.01)
    }

    @Test
    fun testDivision() {
        val division = Division()
        assertEquals(2.0, division.ejecutar(4.0, 2.0), 0.01)
    }

    @Test
    fun testDivisionPorCero() {
        val division = Division()
        assertFailsWith<IllegalArgumentException> {
            division.ejecutar(4.0, 0.0)
        }
    }
}