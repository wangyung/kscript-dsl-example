package idv.freddie.demo.runtime

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class ScriptRuntime {
    private val scriptEngine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")!!

    init { scriptEngine.eval("val x = 1") }

    fun eval(script: String): Any = try {
        val strippedCode = stripIllegalFunction(IMPORTS + script)
            println("running the code $strippedCode")
            scriptEngine.eval(strippedCode)
        } catch (e: IllegalStateException) {
            throw ScriptException(SCRIPT_CANT_RUN)
        }

    private fun stripIllegalFunction(code: String): String =
            code.replace(SYSTEM_EXIT_PATTERN, "")

    companion object {
        private val SYSTEM_EXIT_PATTERN = Regex("System\\.exit\\(\\d*\\)")
        private val IMPORTS = """
            import idv.freddie.demo.dsl.*
            import idv.freddie.demo.runtime.*
            import idv.freddie.demo.runtime.SimpleApp.*
            import idv.freddie.demo.runtime.OkHttpClient.*

        """.trimIndent()
        private const val SCRIPT_CANT_RUN = "Can't run this script."
    }
}
