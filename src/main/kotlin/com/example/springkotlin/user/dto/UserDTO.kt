package com.example.springkotlin.user.dto

import com.example.springkotlin.user.entity.UserEntity
import java.time.LocalDateTime

data class UserDTO(
    val id: Long? = null,                // 사용자 ID
    val createDate: String? = null,      // 생성 날짜 (String으로 변환)
    val email: String? = null,            // 이메일
    val password: String? = null,         // 비밀번호
    val role: String? = null,             // 역할
    val username: String? = null,         // 사용자 이름
    val provider: String? = null,         // 제공자
    val providerId: String? = null        // 제공자 ID
) {

    constructor(entity: UserEntity) : this(
        id = entity.id,
        createDate = entity.createDate?.toString(),
        email = entity.email,
        password = entity.password,
        role = entity.role,
        username = entity.username,
        provider = entity.provider,
        providerId = entity.providerId
    )

    // Entity로 변환하는 메소드
    fun toEntity(): UserEntity {
        return UserEntity(
            id = this.id,
            createDate = this.createDate?.let { LocalDateTime.parse(it) },
            email = this.email ?: "",
            password = this.password ?: "",
            role = this.role ?: "",
            username = this.username ?: "",
            provider = this.provider,
            providerId = this.providerId
        )
    }
}