package org.jetbrains.ktor.tests.locations

import org.jetbrains.ktor.http.*
import org.jetbrains.ktor.testing.*
import org.jetbrains.ktor.tests.*
import kotlin.test.*

fun TestApplicationHost.urlShouldBeHandled(url: String, content: String? = null) {
    on("making get request to $url") {
        val result = handleRequest {
            uri = url
            method = HttpMethod.Get
        }
        it("should be handled") {
            assertTrue(result.requestHandled)
        }
        it("should have a response with OK status") {
            assertEquals(HttpStatusCode.Companion.OK, result.response.status())
        }
        if (content != null) {
            it("should have a response with content '$content'") {
                assertEquals(content, result.response.content)
            }
        }
    }
}

fun TestApplicationHost.urlShouldBeUnhandled(url: String) {
    on("making post request to $url") {
        val result = handleRequest {
            uri = url
            method = HttpMethod.Post
        }
        it("should not be handled") {
            assertFalse(result.requestHandled)
        }
    }
}