package cl.talentodigital.desafiomonstercreator.monsterList.data.local

import android.content.Context
import cl.talentodigital.desafiomonstercreator.database.ServiceDataBase
import cl.talentodigital.desafiomonstercreator.monsterList.domain.MonstersRepository
import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monster
import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monsters
import io.reactivex.Completable
import io.reactivex.Single

class LocalMonstersRepository(
    context: Context,
    private val mapper : MonstersMapper
) : MonstersRepository {

    private val database = ServiceDataBase(context)

    override fun getMonsters(): Single<Monsters> {
        return database
            .getDB()
            .monsterDao()
            .getAll()
            .map { list -> mapper.mapRoomToDomain(list) }
    }

    override fun saveMonster(monster: Monster): Single<Boolean> {
        return database
            .getDB()
            .monsterDao()
            .insertTask(mapper.mapDomainToRoom(monster))
            .map { it > 0 }
    }

    override fun deleteMonsters(): Completable {
        return database
            .getDB()
            .monsterDao()
            .deleteAll()
    }
}