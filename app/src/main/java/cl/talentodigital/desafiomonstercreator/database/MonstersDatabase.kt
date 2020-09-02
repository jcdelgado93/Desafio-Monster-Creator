package cl.talentodigital.desafiomonstercreator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.talentodigital.desafiomonstercreator.monsterList.data.local.database.MonstersDao
import cl.talentodigital.desafiomonstercreator.monsterList.data.local.database.MonstersEntity

@Database(entities = [MonstersEntity::class], version = 1)
abstract class MonstersDatabase : RoomDatabase() {
    abstract fun monsterDao(): MonstersDao
}