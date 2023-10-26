package com.example.lostarkauction.infrastructure.news

import com.example.lostarkauction.infrastructure.news.entity.News
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

interface NewsService {

    fun getNewsByApi(): NewsDto?

    @Transactional
    fun saveNewsByApi(newsDto: NewsDto): Mono<List<News>>
}