package idv.freddie.demo.route

import idv.freddie.demo.runtime.ScriptRuntime
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class RouteHandler {

    private val scriptRuntime: ScriptRuntime = ScriptRuntime()

    fun getApp(request: ServerRequest): Mono<ServerResponse> {
        val code = DEFAULT_CODE
        val model = ModelMap().apply {
            addAttribute("code", code)
        }
        return ServerResponse.ok().render("editor", model)
    }

    fun updateApp(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(Mono.just("{}"), String::class.java)
    }

    companion object {
        private val DEFAULT_CODE = """
        app {
          main {
            println("Hello world")
            return@main 0
          }
        }
    """.trimIndent()
    }
}