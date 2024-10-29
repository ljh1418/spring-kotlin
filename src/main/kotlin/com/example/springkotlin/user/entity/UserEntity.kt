package com.example.springkotlin.user.entity

import com.example.springkotlin.user.dto.UserDTO
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

// data class 사용하여 자동으로 equals(), hashCode(), toString 메서드 생성
// 각 프로퍼티에 ? 추가하여 nullable 타입으로 정의 -> null 가능성을 반영


@Entity(name = "user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var createDate: LocalDateTime? = null,
    var email: String,
    var password: String,
    var role: String,
    var username: String,
    var provider: String? = null,
    var providerId: String? = null
) {

    // 기본 생성자 (기본적으로 데이터 클래스에 포함되지만, 명시적으로 선언할 수도 있습니다.)
    constructor() : this(null, null, "", "", "", "", null, null)

    // DTO로 변환하는 메소드
    fun toDTO(): UserDTO {
        return UserDTO(
            id = this.id,
            createDate = this.createDate?.toString(),
            email = this.email,
            password = this.password,
            role = this.role,
            username = this.username,
            provider = this.provider,
            providerId = this.providerId
        )
    }
}