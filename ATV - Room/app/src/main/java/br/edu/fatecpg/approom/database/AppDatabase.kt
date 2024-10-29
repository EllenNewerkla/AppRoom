package br.edu.fatecpg.approom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.fatecpg.approom.dao.UserDao
import br.edu.fatecpg.approom.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao

}