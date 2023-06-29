package com.example.testkotlinspringgraalvm

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class MyController {

    @PostMapping("/post")
    fun post(@RequestBody request: String): String {

        val restTemplate = RestTemplate()

        return restTemplate.postForObject("https://httpbin.org/post", request, String::class.java)!!
    }

    @GetMapping("/health")
    fun healthCheck(): String {
        return "OK"
    }
}