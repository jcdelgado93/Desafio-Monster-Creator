package cl.talentodigital.desafiomonstercreator.monsterList.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cl.talentodigital.desafiomonstercreator.R
import cl.talentodigital.desafiomonstercreator.databinding.ActivityMainBinding
import cl.talentodigital.desafiomonstercreator.monsterList.data.local.LocalMonstersRepository
import cl.talentodigital.desafiomonstercreator.monsterList.data.local.MonstersMapper
import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monster
import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monsters
import cl.talentodigital.desafiomonstercreator.monsterList.presentation.MonstersState
import cl.talentodigital.desafiomonstercreator.monsterList.presentation.MonstersViewModel
import cl.talentodigital.desafiomonstercreator.monsterList.presentation.MonstersViewModelFactory
import cl.talentodigital.desafiomonstercreator.util.alert

class MainActivity : AppCompatActivity(),
    CreateMonsterCallBack {

    private lateinit var binding : ActivityMainBinding
    private lateinit var dialog : CreateMonsterDialogFragment
    private lateinit var adapter : MonstersAdapter
    private lateinit var viewModel : MonstersViewModel
    private lateinit var viewModelFactory: MonstersViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDialog()
        setupDependencies()
        setupLiveData()
        setupRecyclerView()
        getViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actions_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save_monster -> {
                showDialog()
                true
            }
            R.id.action_delete_monsters -> {
                deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDialog() {
        dialog.show(supportFragmentManager, "String")
    }

    private fun deleteAll() {
        viewModel.deleteMonsters()
    }

    private fun setupDialog() {
        dialog = CreateMonsterDialogFragment(this)
    }

    private fun setupDependencies() {
        viewModelFactory = MonstersViewModelFactory(
            LocalMonstersRepository(
                this,
                MonstersMapper()
            )
        )
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MonstersViewModel::class.java)
    }

    private fun setupLiveData() {
        viewModel.getLiveData()
            .observe(
            this,
                { state -> handleState(state) }
        )
    }

    private fun handleState(state: MonstersState?) {
        when (state) {
            is MonstersState.LoadingState -> showLoading()
            is MonstersState.DeleteMonstersState -> deleteMonsters()
            is MonstersState.CreateMonsterState -> createMonster()
            is MonstersState.GetMonstersState -> state.getResult?.let { showMonsters(it) }
            is MonstersState.ErrorState -> state.error?.let { showError(it) }
        }
    }

    private fun showLoading() {
        alert("Cargando...")
    }

    private fun deleteMonsters() {
        alert("Monstruos borrados.")
    }

    private fun createMonster() {
        alert("Monstruo creado.")
    }

    private fun showMonsters(result: Monsters) {
        adapter = MonstersAdapter(result.monstersList)
        binding.rvMonsterList.adapter = adapter
    }

    private fun showError(error: Throwable) {
        alert(error.message.toString())
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvMonsterList.setHasFixedSize(true)
            rvMonsterList.layoutManager = LinearLayoutManager(this@MainActivity)
            rvMonsterList.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun getViewModel() {
        viewModel.getMonsters()
    }

    override fun processMonster(name: String, points: String, avatar: String) {
        viewModel.createMonster(Monster(name, points, avatar))
    }
}