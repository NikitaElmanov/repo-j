package ru.crub.app.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.crub.app.entity.User

@Repository
interface UserRepository : JpaRepository<User, Long>