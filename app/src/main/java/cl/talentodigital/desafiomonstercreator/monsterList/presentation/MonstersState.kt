package cl.talentodigital.desafiomonstercreator.monsterList.presentation

import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monsters

sealed class MonstersState(
    open val saveResult: Boolean? = null,
    open val getResult: Monsters? = null,
    open val error: Throwable? = null
) {
    object LoadingState : MonstersState()
    object DeleteMonstersState : MonstersState()
    data class CreateMonsterState(override val saveResult: Boolean?): MonstersState(saveResult = saveResult)
    data class GetMonstersState(override val getResult: Monsters?): MonstersState(getResult = getResult)
    data class ErrorState(override val error: Throwable?): MonstersState(error = error)
}