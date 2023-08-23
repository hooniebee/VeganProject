package com.example.vegan

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity
data class Record(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val date: String? = null,
    val diary: String? = null,
    val photoPath: String? = null
)
