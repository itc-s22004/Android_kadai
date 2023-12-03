package jp.ac.it_college.std.s22004.androidkadai.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiClient {
    /**
     * 全てのベースとなる URL
     */
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private val ktor = HttpClient(CIO) {
        engine {
            endpoint {
                connectTimeout = 10000
                requestTimeout = 10000
                socketTimeout = 10000
            }
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }

    public suspend fun get(endpoint: String) =
        ktor.get {
            url("$BASE_URL$endpoint")
        }
}