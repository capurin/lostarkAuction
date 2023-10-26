package com.example.lostarkauction.infrastructure.news

interface NewsService {

    fun getNewsByApi(): NewsDto?
}