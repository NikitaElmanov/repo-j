package ru.crub.app.service

import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import ru.crub.app.entity.User
import ru.crub.app.exception.UserException
import ru.crub.app.repository.UserRepository

@Slf4j
@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow { UserException("User no found") }
    }

    fun createUser(user: User): User = userRepository.save(user)

    fun updateById(id: Long, user: User): User {
        return userRepository.findById(id)
            .map {
                val updatedUser = it.copy(name = user.name, email = user.email)
                userRepository.save(updatedUser)
            }
            .orElse(null)
    }

    fun deleteUserById(id: Long): Boolean {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true
        }

        return false
    }

}