package com.fibenotes.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String? = "",
    var description: String? = "",
    @Ignore
    var isAdd:Boolean=false
) : Parcelable