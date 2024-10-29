package br.edu.fatecpg.approom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid:Int,
    @ColumnInfo(name = "first_name")
    val firstName:String,
    @ColumnInfo(name = "last_name")
    val lastName:String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "foto")
    val foto: String? = null,
    @ColumnInfo(name = "profile_picture_uri")
    val profilePictureUri: String? = null // URL da imagem de perfil
)
