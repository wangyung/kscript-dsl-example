package idv.freddie.demo.runtime

import okhttp3.OkHttpClient
import okhttp3.Request

class OkHttpClient(val domain: String) {
    private val client = OkHttpClient()
    fun get(uri: String = ""): String {
        val request = Request.Builder().url("$domain/$uri").build()
        return client.newCall(request).execute().body?.string() ?: ""
    }
}
