package com.example.lostarkauction.infrastructure.news

class NewsDto : ArrayList<NewsDto.NewsKtItem>(){
    data class NewsKtItem(
        val Date: String,
        val Link: String,
        val Title: String,
        val Type: String
    )
}