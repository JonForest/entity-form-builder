package nz.co.telomere

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import nz.co.telomere.models.Configuration
import nz.co.telomere.models.Entity
import nz.co.telomere.plugins.*

//import kotlinx.serialization.ExperimentalSerializationApi
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import nz.co.telomere.models.Property

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/api").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }

    @Test
    fun testPreview() {
        withTestApplication({ configureRouting() }) {
            application.install(ContentNegotiation) {
                json()
            }
            with(handleRequest(HttpMethod.Post, "/api/preview/ddl") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(
                    Json.encodeToString(
                        Configuration(
                            listOf(
                                Entity(
                                    key = "test",
                                    description = "A test description",
                                    properties = listOf(Property(key="prop1"))
                                )
                            )
                        )
                    )
                )
            }) {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("""CREATE TABLE public."test" (
prop1 text);""", response.content)
            }
        }
    }
}