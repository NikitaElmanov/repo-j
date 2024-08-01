package ru.crub.app.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.crub.app.entity.User
import ru.crub.app.service.UserService

@RestController
@RequestMapping("api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): List<User> = userService.getAllUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long): User = userService.getUserById(id)

    @PostMapping
    fun createUser(@RequestBody user: User): User = userService.createUser(user)

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable("id") id: Long,
        @RequestBody user: User
    ): ResponseEntity<User> {
        val updatedUser = userService.updateById(id, user)

        if (updatedUser == null) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok(updatedUser)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        if (userService.deleteUserById(id)) {
            return ResponseEntity.ok().build()
        }

        return ResponseEntity.notFound().build()
    }

}