package idv.freddie.demo.model

import idv.freddie.demo.dsl.Tag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SimpleApp : Tag("app") {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    internal var entryPoint: (suspend () -> Int)? = null

    internal fun run() = runBlocking {
        scope.launch {
            entryPoint?.invoke()
        }.join()
    }
}
