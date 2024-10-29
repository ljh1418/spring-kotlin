package com.example.springkotlin.user.controller

import com.example.springkotlin.user.dto.UserDTO
import com.example.springkotlin.user.service.impl.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class UserController(private val userService: UserServiceImpl) {

    @GetMapping("/user/findAllUser")
    fun findAllUser(): ResponseEntity<List<UserDTO>> {
        val findAllUser = userService.findAllUser()
        println(findAllUser as Any)
        return ResponseEntity.ok(findAllUser)
    }

    @GetMapping("/user/findUser")
    fun findUser(@PathVariable id: Long): ResponseEntity<UserDTO> {
        val findUser = userService.findUser(id)
        println(findUser as Any)
        return ResponseEntity.ok(findUser)
    }

    @PostMapping("/user/saveUser")
    fun saveUser(@RequestBody userDTO: UserDTO): ResponseEntity<UserDTO> {
        println(userDTO as Any)
        val saveUser = userService.saveUser(userDTO)
        println(saveUser as Any)
        return ResponseEntity.ok(saveUser)
    }

    @PutMapping("/user/updateUser/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody userDTO: UserDTO): Long? =
        userService.updateUser(id, userDTO)

    @DeleteMapping("/user/deleteUser/{id}")
    fun deleteUser(@PathVariable id: Long): Long? =
        userService.deleteUser(id)

}