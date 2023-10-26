package com.example.lostarkauction.infrastructure.news.repository

import com.example.lostarkauction.infrastructure.news.entity.News
import org.springframework.data.repository.reactive.ReactiveCrudRepository


interface NewsRepository: ReactiveCrudRepository<News, Long> {
}