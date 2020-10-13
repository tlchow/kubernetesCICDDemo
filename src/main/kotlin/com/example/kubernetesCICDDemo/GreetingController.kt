package com.example.kubernetesCICDDemo

import java.util.concurrent.atomic.AtomicLong
import org.springframework.web.bind.annotation.*

@RestController
class GreetingController {
    val counter = AtomicLong()
    
    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting {
        val envVar: String = System.getenv("EXAMPLE_VALUE") ?: "default_value"
        return Greeting(counter.incrementAndGet(), "Hello, $name", envVar)
    }
}