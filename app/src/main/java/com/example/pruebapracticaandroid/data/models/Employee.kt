package com.example.pruebapracticaandroid.data.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "surname")
    val surname: String,
    @ColumnInfo(name = "job")
    val job: String,
    @ColumnInfo(name = "phone")
    val phone: Int,
    @ColumnInfo(name = "mail")
    val mail: String,
    @ColumnInfo(name = "imageId")
    val imageId: Int = 0
) : Parcelable