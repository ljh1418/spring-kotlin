package com.example.springkotlin.user.service

import com.example.springkotlin.user.dto.UserDTO

interface UserService {

    fun findAllUser(): List<UserDTO>
    fun findUser(id: Long): UserDTO?
    fun saveUser(userDTO: UserDTO): UserDTO
    fun updateUser(id: Long, userDTO: UserDTO): Long?
    fun deleteUser(id: Long): Long?

}