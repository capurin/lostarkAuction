package com.example.lostarkauction.interfaces

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/")
    fun greetings() :String {
        return "hello";
    }

}