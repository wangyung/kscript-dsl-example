package idv.freddie.demo.runtime

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class ScriptRuntime {
    private val scriptEngine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")!!

    fun eval(script: String): Any =
        scriptEngine.eval(IMPORTS + script)

    companion object {
        private val IMPORTS = """
            import idv.freddie.demo.model.SimpleApp
            import idv.freddie.demo.dsl.*

        """.trimIndent()
    }
}