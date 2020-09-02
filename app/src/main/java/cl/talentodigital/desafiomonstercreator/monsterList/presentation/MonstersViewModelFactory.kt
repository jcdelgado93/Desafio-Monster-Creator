package cl.talentodigital.desafiomonstercreator.monsterList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.desafiomonstercreator.monsterList.domain.MonstersRepository

class MonstersViewModelFactory(
    private val repository: MonstersRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(MonstersRepository::class.java)
            .newInstance(repository)
    }
}