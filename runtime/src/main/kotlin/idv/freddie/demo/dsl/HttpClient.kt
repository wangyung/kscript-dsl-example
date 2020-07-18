package idv.freddie.demo.dsl

import idv.freddie.demo.runtime.OkHttpClient

fun httpClient(domain: String) = OkHttpClient(domain)