package cl.talentodigital.desafiomonstercreator.monsterList.data.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monstersDB")
data class MonstersEntity(
    @ColumnInfo(name = "monster") val name: String,
    @ColumnInfo(name = "monsterPoints") val monsterPoints: String,
    @ColumnInfo(name = "image") val image: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}