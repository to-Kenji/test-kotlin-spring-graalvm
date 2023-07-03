package com.example.testkotlinspringgraalvm

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class MyController {

    @PostMapping("/post")
    fun post(@RequestBody req: String): String {

        println("-------------------------------------------MyController-------------------------------------------------")
        val restTemplate = RestTemplate()

        val request = MyRequest(req, "test")
        val response: String;
        try {
            // TODO: exchangeも試す
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

data class MyRequest(val requestBody: String, val task: String)

// TODO: 以下のようなネストしたクラスを試す
// data class MyChildRequest(val title: String, val description: String)