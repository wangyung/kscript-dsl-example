package idv.freddie.demo.route

import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class AppRoute(private val routeHandler: RouteHandler) {
    @Bean
    fun staticRoute() = router {
        (accept(MediaType.APPLICATION_JSON) and "/").nest {
            GET("/", routeHandler::showEditor)
            POST("/run", routeHandler::runApp)
        }
    }
}
