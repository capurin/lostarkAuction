package com.example.lostarkauction.interfaces

import com.example.lostarkauction.infrastructure.news.NewsDto
import com.example.lostarkauction.infrastructure.news.NewsService
import com.example.lostarkauction.infrastructure.news.NewsServiceImpl
import com.example.lostarkauction.infrastructure.news.repository.NewsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class HelloController @Autowired constructor(val newsService: NewsServiceImpl) {

    @GetMapping("/")
    suspend fun greetings(): String {
        return "hello";
    }

    @GetMapping("/getNews")
    suspend fun getNews(): ResponseEntity<Any> {
        val response = newsService.getNewsByApi()
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveNews")
    suspend fun saveNews(): ResponseEntity<Any> {
        val newsList = newsService.getNewsByApi() ?: throw Exception()
        val response = newsService.saveNewsByApi(newsList)
        return ResponseEntity.ok(response);
    }

}