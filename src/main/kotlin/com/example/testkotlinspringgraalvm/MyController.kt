package com.example.testkotlinspringgraalvm

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
@RestController
class MyController {

    @PostMapping("/post")
    @RegisterReflectionForBinding(MyRequest::class)
    fun post(@RequestBody req: MyRequest): String {

        println(req)
        val restTemplate = RestTemplate()

        val response: String;
        try {
            response = restTemplate.postForObject("http://jsonplaceholder.typicode.com/posts", req, String::class.java)!!
        } catch (e: Exception) {
            throw e
        }
        return response
    }
}

data class MyRequest(val str: String)
// data class MyApiRequest(val requestBody: String, val task: String)


// data class MyChildApiRequest(val title: String, val description: String)

// @Configuration
// @RegisterReflectionForBinding(RestTemplate::class)
// @ImportRuntimeHints(MyConfiguration.AppRunTimeHintsRegister::class)
// class MyConfiguration {
//     class AppRunTimeHintsRegister : RuntimeHintsRegistrar {
//         override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
//             hints.reflection().registerType(RestTemplate::class.java, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.INVOKE_DECLARED_METHODS, MemberCategory.DECLARED_CLASSES)
//         }
//     }
// }
