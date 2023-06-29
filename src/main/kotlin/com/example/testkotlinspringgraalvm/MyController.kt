package com.example.testkotlinspringgraalvm

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController {

    @GetMapping("/health")
    fun healthCheck(): String {
        return "OK"
    }
}