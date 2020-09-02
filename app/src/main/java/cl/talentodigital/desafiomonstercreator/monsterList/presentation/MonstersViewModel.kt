package cl.talentodigital.desafiomonstercreator.monsterList.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.talentodigital.desafiomonstercreator.monsterList.domain.MonstersRepository
import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monster
import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monsters
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MonstersViewModel(
    private val repository: MonstersRepository
) : ViewModel() {

    private val liveData = MutableLiveData<MonstersState>()
    private val compositeDisposable = CompositeDisposable()

    fun getLiveData() = liveData

    fun createMonster(monster: Monster) {
        liveData.postValue(MonstersState.LoadingState)
        compositeDisposable.add(repository
            .saveMonster(monster)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleResult(result) },
                { error -> handleError(error) }
            )
        )
    }

    fun deleteMonsters() {
        liveData.postValue(MonstersState.LoadingState)
        compositeDisposable.add(repository
            .deleteMonsters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handleCompletion() },
                { error -> handleError(error) }
            )
        )
    }

    fun getMonsters() {
        liveData.postValue(MonstersState.LoadingState)
        compositeDisposable.add(repository
            .getMonsters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleResult(result) },
                { error -> handleError(error) }
            )
        )
    }

    private fun handleResult(result: Boolean) {
        liveData.postValue(MonstersState.CreateMonsterState(result))
    }

    private fun handleResult(result: Monsters) {
        liveData.postValue(MonstersState.GetMonstersState(result))
    }

    private fun handleCompletion() {
        liveData.postValue(MonstersState.DeleteMonstersState)
    }

    private fun handleError(error: Throwable) {
        liveData.postValue(MonstersState.ErrorState(error))
    }
}