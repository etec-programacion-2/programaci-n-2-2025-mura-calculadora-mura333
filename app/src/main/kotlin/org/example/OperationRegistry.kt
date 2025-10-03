// OperationRegistry.kt
package org.example

class OperationRegistry {
    private val operations = mutableMapOf<String, Operation>()

    fun register(symbol: String, operation: Operation) {
        operations[symbol] = operation
    }

    fun getOperation(symbol: String): Operation? {
        return operations[symbol]
    }
}