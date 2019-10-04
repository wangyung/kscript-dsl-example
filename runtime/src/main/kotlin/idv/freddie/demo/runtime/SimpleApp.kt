package idv.freddie.demo.runtime

import idv.freddie.demo.dsl.Tag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class SimpleApp : Tag("app") {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    internal var entryPoint: (suspend () -> Int)? = null

    private val outStream: ByteArrayOutputStream = ByteArrayOutputStream(2 * 1024) // 2k for output
    internal val outPrintStream: PrintStream = PrintStream(outStream)

    val appId: Int

    init {
        appId = 10000 + (Math.random() * 10000).toInt()
        System.setOut(outPrintStream)
        System.setErr(outPrintStream)
    }

    val stdout: String
        get() {
            outPrintStream.flush()
            return outStream.toString().also {
                outStream.reset()
            }
        }

    fun run(): Int = runBlocking {
        var result: Int = -1
        scope.launch {
            result = entryPoint?.invoke() ?: -1
        }.join()
        return@runBlocking result
    }
}
