package com.example.lostarkauction.infrastructure.news

import com.example.lostarkauction.infrastructure.news.entity.News
import java.time.LocalDateTime

class NewsDto : ArrayList<NewsDto.NewsKtItem>() {
    data class NewsKtItem(
            val id : Long,
            val Date: LocalDateTime,
            val Link: String,
            val Title: String,
            val Type: String
    )

    fun toListEntity(): List<News> {
        return this.stream().map {
            val id = it.Link.substring((it.Link.indexOfLast { s -> s == '/' })+1, it.Link.length, ).toLong()
            News(id = id, type = it.Type, date = it.Date, link = it.Link, title = it.Title)
        }.toList()
    }

}