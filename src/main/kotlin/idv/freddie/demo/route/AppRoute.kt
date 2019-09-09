package idv.freddie.demo.route

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class AppRoute(private val routeHandler: RouteHandler) {

    @Value("classpath:/static/index.html")
    private lateinit var indexHtml: Resource

    @Bean
    fun staticRoute() = router {
        GET("/") { req ->
            ok().contentType(MediaType.TEXT_HTML).syncBody(indexHtml)
        }
        (accept(MediaType.APPLICATION_JSON) and "/").nest {
            GET("/app/{appId}", routeHandler::getApp)
            POST("/app/{appId}", routeHandler::updateApp)
        }
    }
}
