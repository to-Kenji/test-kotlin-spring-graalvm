package com.example.testkotlinspringgraalvm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestKotlinSpringGraalvmApplication

fun main(args: Array<String>) {
	runApplication<TestKotlinSpringGraalvmApplication>(*args)
}
