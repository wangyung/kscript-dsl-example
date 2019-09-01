package idv.freddie.demo

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class ScripRuntime {
    private val scriptEngine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")!!

    fun eval(script: String): Any =
        scriptEngine.eval(script)
}