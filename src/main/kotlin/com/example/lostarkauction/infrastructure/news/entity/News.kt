package com.example.lostarkauction.infrastructure.news.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table
data class News
(
        @Id
        var id: Long? = null,

        @Column
        var type: String? = null,

        @Column
        var date: LocalDateTime? = null,
        @Column
        var title: String? = null,
        @Column
        var link: String? = null,

        @Column
        @CreatedDate
        var createdAt: LocalDateTime? = null,

        @Column
        @LastModifiedDate
        var modifiedAt: LocalDateTime? = null

)