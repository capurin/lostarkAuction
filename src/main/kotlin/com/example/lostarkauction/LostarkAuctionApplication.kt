package com.example.lostarkauction

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer

@SpringBootApplication
class LostarkAuctionApplication

fun main(args: Array<String>) {
    runApplication<LostarkAuctionApplication>(*args)
}
