package com.example.testkotlinspringgraalvm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class MyController {

    @PostMapping("/post")
    @RegisterReflectionForBinding(MyApiRequest::class)
    fun post(@RequestBody req: MyRequest): MyApiResponse{

        val restTemplate = RestTemplate()

        val request = MyApiRequest(req.str, "test")
        val response: MyApiResponse
        try {
            response = restTemplate.postForObject("https://httpbin.org/post", request, MyApiResponse::class.java)!!
        } catch (e: Exception) {
            throw e
        }
        return response
    }

    @GetMapping("/health")
    fun healthCheck(): String {
        return "OK"
    }
}

data class MyRequest(val str: String)
data class MyApiRequest(val requestBody: String, val task: String)
@Serializable
data class MyApiResponse(
    @SerialName("args")
    val args: MyApiResponseArgs,
    @SerialName("data")
    val data: String,
    @SerialName("files")
    val files: MyApiResponseFiles,
    @SerialName("form")
    val form: MyApiResponseForm,
    @SerialName("headers")
    val headers: MyApiResponseHeaders,
    @SerialName("json")
    val json: MyApiResponseJson,
    @SerialName("origin")
    val origin: String,
    @SerialName("url")
    val url: String
)
@Serializable
data class MyApiResponseJson(
    @SerialName("requestBody")
    val requestBody: String,
    @SerialName("task")
    val task: String
)

@Serializable
class MyApiResponseArgs{}

@Serializable
class MyApiResponseFiles{}

@Serializable
class MyApiResponseForm{}

@Serializable
class MyApiResponseHeaders(
    @SerialName("Accept")
    val accept: String,
    @SerialName("Content-Length")
    val contentLength: String,
    @SerialName("Content-Type")
    val contentType: String,
    @SerialName("Host")
    val host: String,
    @SerialName("User-Agent")
    val userAgent: String,
    @SerialName("X-Amzn-Trace-Id")
    val xAmznTraceId: String
)