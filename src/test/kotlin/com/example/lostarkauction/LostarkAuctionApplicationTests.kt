package com.example.lostarkauction

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.*
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory

import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestTemplate


@SpringBootTest
class LostarkAuctionApplicationTests {

    @Test
    fun contextLoads() {

        var jsonInString = ""

        try {
            val factory = HttpComponentsClientHttpRequestFactory();

            factory.setConnectTimeout(5000)

            val restTemplate = RestTemplate(factory)
            //restTemplate은 Rest방식 api를 호출할 수 있는 spring 내장 클래스이다.
            //json, xml 응답을 모두 받을 수 있다.

            //header 클래스를 정의해 주고, url을 정의해 주고 exchange method로 api를 호출한다.
            val header = HttpHeaders()
            header.contentType= MediaType.parseMediaType("application/json")
            header.setBearerAuth("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAyOTg0MjUifQ.I7sA9qLun29-MRgJhIAzH2tEp7Bq71gZbWd5ODyXqCpKDh8LfVROcw6n476MYI7goIGQ7L25PmD8TQxXkviK2csv4B7v7q9-vmVNTZyxEMKsWYNusZQ3s46WhSie_9yT-SaYw744G1yrcjEv0e6EtcDxlkIvYL5Fzr_3z65_tp3D471UIF_y4ugPK9ealPW8dsyiPGpOAgK0SyZ_rS47OpoKX_Yq6cSHsKwX_7kXhgQbJwZNGzArfjLNvs0eAO65lHO3UGQ4OOzQhBiG9ZeP-CrID0cm4J_m3wvMeckxyVJRxlZmGwkiCivljD4idswgts9ispuf8qkWzv__7iqkMg")
            header.accept = MediaType.parseMediaTypes("application/json")

            val entity = HttpEntity<List<Map<String, Any>>>(header)
            val url = "https://developer-lostark.game.onstove.com/news/notices"

            //api를 호출하여 데이터를 MAP타입으로 전달 받는다.
            val resultMap : ResponseEntity<List<*>>
                    = restTemplate.exchange(url, HttpMethod.GET, entity, List::class.java)

//            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
//            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
//            resultMap.body?.let { result.put("body", it) };
//            //result.put("body", resultMap.getBody())로 넣을 수 없었다. null 가능성 때문인 것 같다.

            //데이터를 string형태로 파싱해줌
            val mapper = ObjectMapper()
            jsonInString = mapper.writeValueAsString(resultMap.getBody());

        } catch (e: Exception){
            when(e) {
                is HttpClientErrorException, is HttpServerErrorException -> {
                    System.out.println("error!");
                    System.out.println(e.toString());
                }else -> {
                System.out.println(e.toString());
            }
            }
        }

        System.out.println(jsonInString)
    }

}
