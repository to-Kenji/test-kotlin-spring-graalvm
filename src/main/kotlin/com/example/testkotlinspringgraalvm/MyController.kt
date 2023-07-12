package com.example.testkotlinspringgraalvm

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
@RestController
class MyController {

    @PostMapping("/post")
    @RegisterReflectionForBinding(MyRequest::class, MyApiRequest::class)
    fun post(@RequestBody req: MyRequest): String {

        val restTemplate = RestTemplate()
        val myApiRequest = MyApiRequest(req.str, "test")

        val response: String;
        try {
            response = restTemplate.postForObject("http://jsonplaceholder.typicode.com/posts", myApiRequest, String::class.java)!!
        } catch (e: Exception) {
            throw e
        }
        return response
    }
}

data class MyRequest(val str: String)
data class MyApiRequest(val requestBody: String, val task: String)

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
