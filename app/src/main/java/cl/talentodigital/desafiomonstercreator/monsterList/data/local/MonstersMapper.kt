package cl.talentodigital.desafiomonstercreator.monsterList.data.local

import cl.talentodigital.desafiomonstercreator.monsterList.data.local.database.MonstersEntity
import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monster
import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monsters

class MonstersMapper {

    fun mapDomainToRoom(monsters: Monster): MonstersEntity {
        return MonstersEntity(
            name = monsters.name,
            monsterPoints = monsters.monsterPoints,
            image = monsters.image
        )
    }

    fun mapRoomToDomain(list: List<MonstersEntity>): Monsters {
        return Monsters(list.map{
            Monster(it.name, it.monsterPoints, it.image)
        })
    }
}