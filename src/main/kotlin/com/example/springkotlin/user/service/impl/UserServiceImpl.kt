package com.example.springkotlin.user.service.impl


import com.example.springkotlin.user.dto.UserDTO
import com.example.springkotlin.user.repository.UserRepository
import com.example.springkotlin.user.service.UserService
import org.springframework.data.repository.findByIdOrNull

import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    //asSequence()는 Kotlin에서 제공하는 확장 함수로, 컬렉션을 시퀀스(Sequence)로 변환하는 데 사용됩니다.
    //주요 특징
    //Lazy Evaluation:시퀀스는 컬렉션의 모든 요소를 즉시 처리하지 않고, 필요한 순간에만 계산합니다. 예를 들어, 연산을 연결할 경우 마지막에 toList()와 같은 함수를 호출할 때까지 요소가 계산되지 않습니다.
    //메모리 효율성:큰 데이터셋을 처리할 때, 시퀀스를 사용하면 모든 데이터를 한 번에 메모리에 로드하지 않아도 됩니다. 대신 필요한 부분만 처리하여 메모리 사용을 줄일 수 있습니다.
    //연산 체이닝:시퀀스는 여러 변환(예: map, filter)을 체이닝하여 사용할 수 있으며, 이러한 연산들은 필요한 순간에 수행됩니다.
    override fun findAllUser(): List<UserDTO> {
        return userRepository.findAll()?.asSequence()?.map { it.toDTO() }?.toList() ?: emptyList()
    }

    override fun findUser(id: Long): UserDTO {
        val findUser = userRepository.findByIdOrNull(id) ?: throw NoSuchElementException("ID = $id, 조회 중 해당하는 회원이 없습니다.")
        return UserDTO(findUser)
    }

    override fun saveUser(userDTO: UserDTO): UserDTO {

        if (userDTO.email.isNullOrBlank() || userDTO.username.isNullOrBlank()) {
            throw IllegalArgumentException("이메일과 사용자 이름은 필수입니다.")
        }

        val userEntity = userDTO.toEntity()
        val saveUser = userRepository.save(userEntity)
        return saveUser?.toDTO() ?: throw RuntimeException("사용자 저장에 실패했습니다.")
    }

    override fun updateUser(id: Long, userDTO: UserDTO): Long? {
        var updateUser = userRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("id = $id, 수정중 해당하는 회원이 없습니다.")

        updateUser.email = userDTO.email ?: updateUser.email
        updateUser.username = userDTO.username ?: updateUser.username
        userRepository.save(updateUser)

        return id
    }

    //Kotlin에서 Long?와 Long의 차이는 null 허용 여부입니다.
    override fun deleteUser(id: Long): Long? {
        val deleteUser = userRepository.findByIdOrNull(id)
        if (deleteUser == null) {
            println("사용자를 찾을 수 없습니다. ID: $id") // 디버깅 로그 추가
            //throw NoSuchElementException("사용자를 찾을 수 없습니다. ID: $id") // 예외 던지기
        }

        userRepository.deleteById(id)
        return id // 삭제 성공 시 ID 반환
    }


}