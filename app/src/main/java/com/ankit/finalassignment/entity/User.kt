package com.ankit.finalassignmentproject.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    var username: String? = null,
    var password: String? = null,
    var email: String? = null,
    var phone: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}