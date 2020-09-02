package cl.talentodigital.desafiomonstercreator.monsterList.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MonstersDao {
    @Query("SELECT * FROM monstersDB")
    fun getAll(): Single<List<MonstersEntity>>

    @Insert
    fun insertTask(monstersEntity: MonstersEntity): Single<Long>

    @Query("DELETE FROM monstersDB")
    fun deleteAll(): Completable
}