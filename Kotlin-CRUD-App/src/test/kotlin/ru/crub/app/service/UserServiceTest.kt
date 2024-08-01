package ru.crub.app.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.crub.app.entity.User
import ru.crub.app.repository.UserRepository
import java.util.*

@ExtendWith(MockKExtension::class)
internal class UserServiceTest {

    @MockK
    lateinit var userRepository: UserRepository

    @InjectMockKs
    lateinit var userService: UserService

    @Test
    fun getAllUsers() {
        // Given
        val expectedUser = User(1, "Tom", "tom.x5.ru")
        every { userRepository.findAll() } returns listOf(expectedUser)

        // When
        val actualUsers = userService.getAllUsers()

        // Then
        assertThat(actualUsers)
            .isNotEmpty
            .hasSize(1)

        assertThat(actualUsers[0])
            .isNotNull
            .isEqualTo(expectedUser)

        verify(exactly = 1) { userRepository.findAll() }
        verify(exactly = 0) { userRepository.save(any()) }
    }

    @Test
    fun getUserById() {
        // TODO
    }

    @Test
    fun createUser() {
        // TODO
    }

    @Test
    fun updateById() {
        // Given
        val expectedUser = User(1, "Tomas", "tom.x5.ru")
        every { userRepository.findById(1) } returns Optional.of(expectedUser)
        every { userRepository.save(any()) } returns expectedUser

        // When
        val actualUser = userService.updateById(1, expectedUser)

        // Then
        assertThat(actualUser)
            .isNotNull
            .isEqualTo(expectedUser)

        verify(exactly = 0) { userRepository.findAll() }
        verify(exactly = 1) { userRepository.findById(1) }
        verify(exactly = 1) { userRepository.save(any()) }
    }

    @Test
    fun deleteUserById() {
        // TODO
    }
}