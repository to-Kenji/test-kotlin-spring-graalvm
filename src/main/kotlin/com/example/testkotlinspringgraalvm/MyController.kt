package com.example.testkotlinspringgraalvm

import org.springframework.aot.hint.MemberCategory
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportRuntimeHints
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class MyController {

    @PostMapping("/post")
    @RegisterReflectionForBinding(MyApiRequest::class)
    fun post(@RequestBody req: MyRequest): String {

        val restTemplate = RestTemplate()

        val request = MyApiRequest(req.str, "test")
        val response: String;
        try {
            response = restTemplate.postForObject("https://httpbin.org/post", request, String::class.java)!!
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