package com.vaxapp.covid19.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DatabaseResponse(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: String,
    val countyCode: Int,
    val countyName: String,
    val townCode: Int,
    val townName: String,
    val sexCode: Int,
    val sexName: String,
    val resultType: String,
    val casesNumber: Int
)
