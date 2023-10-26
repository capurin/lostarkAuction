package com.example.lostarkauction.interfaces

import com.example.lostarkauction.infrastructure.news.NewsDto
import com.example.lostarkauction.infrastructure.news.NewsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @Autowired
    private lateinit var newsService: NewsService;
    @GetMapping("/")
    fun greetings() :String {
        return "hello";
    }

    @GetMapping("/getNews")
    fun getNews() :ResponseEntity<NewsDto> {
        val response = newsService.getNewsByApi()
        return ResponseEntity.ok(response);
    }

}