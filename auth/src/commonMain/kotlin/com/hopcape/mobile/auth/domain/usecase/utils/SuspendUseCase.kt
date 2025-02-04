package com.hopcape.mobile.auth.domain.usecase.utils

/**
 * Represents a use case that can be executed asynchronously using Kotlin coroutines.
 *
 * This interface defines a contract for use cases that take an input parameter of type [I],
 * perform some asynchronous operation, and return a result of type [O]. It leverages Kotlin's
 * `suspend` functions to ensure that the operation can be safely executed in a coroutine context.
 *
 * Example usage:
 * ```kotlin
 * // Define a use case that takes a String as input and returns an Int as output
 * class ExampleUseCase : SuspendUseCase<String, Int> {
 *     override suspend fun invoke(params: String): Int {
 *         // Simulate an asynchronous operation (e.g., network call or database query)
 *         return params.length
 *     }
 * }
 *
 * // Usage of the use case
 * suspend fun executeExampleUseCase(useCase: SuspendUseCase<String, Int>) {
 *     val input = "Hello, World!"
 *     val result = useCase(input) // Invokes the use case
 *     println("The length of the input string is: $result") // Output: "The length of the input string is: 13"
 * }
 *
 * // Example invocation
 * val useCase = ExampleUseCase()
 * executeExampleUseCase(useCase)
 * ```
 *
 * @param I The type of the input parameter for the use case.
 * @param O The type of the output result produced by the use case.
 */
interface SuspendUseCase<I, O> {

    /**
     * Executes the use case with the given input parameters.
     *
     * This function is marked as `suspend`, meaning it can perform asynchronous operations
     * without blocking the calling thread. It takes an input parameter of type [I] and
     * returns a result of type [O].
     *
     * Example usage:
     * ```kotlin
     * // Define a simple use case implementation
     * class MultiplyUseCase : SuspendUseCase<Int, Int> {
     *     override suspend fun invoke(params: Int): Int {
     *         return params * 2
     *     }
     * }
     *
     * // Execute the use case
     * suspend fun testMultiplyUseCase(useCase: SuspendUseCase<Int, Int>) {
     *     val input = 5
     *     val result = useCase(input) // Invokes the use case
     *     println("Result of multiplying $input by 2 is: $result") // Output: "Result of multiplying 5 by 2 is: 10"
     * }
     *
     * // Example invocation
     * val multiplyUseCase = MultiplyUseCase()
     * testMultiplyUseCase(multiplyUseCase)
     * ```
     *
     * @param params The input parameter required to execute the use case.
     * @return The result produced by the use case after execution.
     */
    suspend operator fun invoke(params: I): O
}