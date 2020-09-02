package cl.talentodigital.desafiomonstercreator.database

import android.content.Context
import androidx.room.Room

private const val DATA_BASE_NAME = "desafio-monsters.db"
class ServiceDataBase(
    applicationContext: Context
) {
    private val db = Room.databaseBuilder(
        applicationContext,
        MonstersDatabase::class.java,
        DATA_BASE_NAME
    ).build()

    fun getDB() = db
}