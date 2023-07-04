package com.example.testkotlinspringgraalvm

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class MyController {

    @PostMapping("/post")
    fun post(@RequestBody req: MyRequest): String {

        println("-------------------------------------------MyController-------------------------------------------------")
        val restTemplate = RestTemplate()

        val request = MyApiRequest()
        request.requestBody = req.str
        request.task = "task1"

        println("generated api request: $request")
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
class MyApiRequest{
    var requestBody: String = "initial"
    var task: String = ""
    override fun toString(): String {
        return "MyApiRequest(requestBody='$requestBody', task='$task')"
    }
}

// data class MyChildApiRequest(val title: String, val description: String)