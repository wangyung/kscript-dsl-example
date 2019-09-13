package idv.freddie.demo.route

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import idv.freddie.demo.model.Code
import idv.freddie.demo.runtime.SimpleApp
import idv.freddie.demo.runtime.ScriptRuntime
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class RouteHandler {

    private val scriptRuntime: ScriptRuntime = ScriptRuntime()
    private val mapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())

    fun showEditor(request: ServerRequest): Mono<ServerResponse> {
        val code = DEFAULT_CODE
        val model = ModelMap().apply {
            addAttribute("code", code)
        }
        return ServerResponse.ok().render("editor", model)
    }

    fun runApp(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(String::class.java)
            .map {
                mapper.readValue(it, Code::class.java)
            }
            .flatMap {
                val responseText: String = try {
                    val app = scriptRuntime.eval(it.code) as? SimpleApp
                    app?.let {
                        val returnValue = app.run()
                        app.stdout
                    } ?: "Can't run the app"
                } catch (e: Exception) {
                    e.message ?: e.toString()
                }
                ServerResponse.ok().body(BodyInserters.fromObject(responseText))
            }

    companion object {
        private const val APP_ID_VARIABLE = "\$appId"
        private val DEFAULT_CODE = """
        app {
          main {
            println("Hello world, id=$APP_ID_VARIABLE")
            return@main 0
          }
        }
    """.trimIndent()
    }
}