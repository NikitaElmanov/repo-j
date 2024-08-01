package basic.coroutines

import kotlinx.coroutines.*
import java.util.*

fun main(): Unit = runBlocking {
    val listCoroutines: List<Deferred<String>> = List(10) {
        async(start = CoroutineStart.DEFAULT) {
            doWork("as $it")
        }
    }

    listCoroutines.forEach {
        it.cancel()
    }
}

suspend fun doWork(message: String): String {
    delay(Random().nextInt(5000).toLong())
    return "Done. $message"
}