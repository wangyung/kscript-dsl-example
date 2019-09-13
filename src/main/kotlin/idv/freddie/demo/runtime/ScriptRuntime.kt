package idv.freddie.demo.runtime

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class ScriptRuntime {
    private val scriptEngine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")!!

    init { scriptEngine.eval("val x = 1") }

    fun eval(script: String): Any = try {
            println("running the code ${IMPORTS + script}")
            scriptEngine.eval(IMPORTS + script)
        } catch (e: IllegalStateException) {
            throw ScriptException(SCRIPT_CANT_RUN)
        }

    companion object {
        private val IMPORTS = """
            import idv.freddie.demo.dsl.*
            import idv.freddie.demo.runtime.*
            import idv.freddie.demo.runtime.SimpleApp.*

        """.trimIndent()
        private const val SCRIPT_CANT_RUN = "Can't run this script."
    }
}
