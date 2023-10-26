package com.example.lostarkauction.infrastructure.news

import com.example.lostarkauction.infrastructure.news.entity.News
import com.example.lostarkauction.infrastructure.news.repository.NewsRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class NewsServiceImpl (private val newsRepository: NewsRepository) {

    fun getNewsByApi(): NewsDto? {
        var result: NewsDto? = null;
        try {
            val factory = HttpComponentsClientHttpRequestFactory();

            factory.setConnectTimeout(5000)

            val restTemplate = RestTemplate(factory)
            //restTemplate은 Rest방식 api를 호출할 수 있는 spring 내장 클래스이다.
            //json, xml 응답을 모두 받을 수 있다.

            //header 클래스를 정의해 주고, url을 정의해 주고 exchange method로 api를 호출한다.
            val header = HttpHeaders()
            header.contentType = MediaType.parseMediaType("application/json")
            header.setBearerAuth("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAyOTg0MjUifQ.I7sA9qLun29-MRgJhIAzH2tEp7Bq71gZbWd5ODyXqCpKDh8LfVROcw6n476MYI7goIGQ7L25PmD8TQxXkviK2csv4B7v7q9-vmVNTZyxEMKsWYNusZQ3s46WhSie_9yT-SaYw744G1yrcjEv0e6EtcDxlkIvYL5Fzr_3z65_tp3D471UIF_y4ugPK9ealPW8dsyiPGpOAgK0SyZ_rS47OpoKX_Yq6cSHsKwX_7kXhgQbJwZNGzArfjLNvs0eAO65lHO3UGQ4OOzQhBiG9ZeP-CrID0cm4J_m3wvMeckxyVJRxlZmGwkiCivljD4idswgts9ispuf8qkWzv__7iqkMg")
            header.accept = MediaType.parseMediaTypes("application/json")

            val entity = HttpEntity<List<Map<String, Any>>>(header)
            val url = "https://developer-lostark.game.onstove.com/news/notices"

            //api를 호출하여 데이터를 MAP타입으로 전달 받는다.
            val resultMap: ResponseEntity<NewsDto> = restTemplate.exchange(url, HttpMethod.GET, entity, NewsDto::class)

            //데이터를 string형태로 파싱해줌
            result = resultMap.body;
            // jsonInString = ObjectMapper().readValue(resultMap.toString(), News::class.java)
            return result;
        } catch (e: Exception) {
            when (e) {
                is HttpClientErrorException, is HttpServerErrorException -> {
                    System.out.println("error!");
                    System.out.println(e.toString());
                }

                else -> {
                    System.out.println(e.toString());
                }
            }
        }
        return result;

    }

    fun saveNewsByApi(newsDto: NewsDto): Flux<News> {
        return newsRepository.saveAll(newsDto.toListEntity())
    }


}