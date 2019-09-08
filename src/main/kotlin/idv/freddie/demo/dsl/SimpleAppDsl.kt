package idv.freddie.demo.dsl

import idv.freddie.demo.model.SimpleApp

fun app(init: SimpleApp.() -> Unit): SimpleApp = SimpleApp().apply(init)

fun SimpleApp.main(init: suspend () -> Int) {
    this.entryPoint = init
}
