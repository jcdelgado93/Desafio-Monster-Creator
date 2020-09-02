package cl.talentodigital.desafiomonstercreator.monsterList.domain

import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monster
import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monsters
import io.reactivex.Completable
import io.reactivex.Single

interface MonstersRepository {
    fun getMonsters(): Single<Monsters>
    fun saveMonster(monster: Monster): Single<Boolean>
    fun deleteMonsters(): Completable
}