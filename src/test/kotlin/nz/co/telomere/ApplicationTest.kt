package nz.co.telomere

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import kotlin.test.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import nz.co.telomere.models.Entity
import nz.co.telomere.plugins.*

val config = """
    {'entities': [
        {'name': 'test', 'description': 'A test value' }
    ]}
""".trimIndent().replace("'", "\"")

val entity = """
    {'name': 'test', 'description': 'A test value' }
""".trimIndent().replace("'", "\"")
class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }

//    @Test
//    fun testPreview() {
//        withTestApplication({ configureRouting() }) {
//            with(handleRequest(HttpMethod.Post, "/preview", ) {
//                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
//                setBody(Json.encodeToString(Entity(name="test", description="A test description")))
//            }) {
//                assertEquals(HttpStatusCode.OK, response.status())
//                assertEquals("test", response.content)
//            }
//        }
//    }
}