package cl.talentodigital.desafiomonstercreator.monsterList.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.talentodigital.desafiomonstercreator.databinding.ItemMonsterBinding
import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monster

class MonstersAdapter(
    private val monsters: List<Monster>
) : RecyclerView.Adapter<MonstersViewHolder>() {

    private lateinit var binding: ItemMonsterBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonstersViewHolder {
        binding = ItemMonsterBinding.inflate(LayoutInflater.from(parent.context))
        return MonstersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MonstersViewHolder, position: Int) {
        holder.bind(monsters[position])
    }

    override fun getItemCount(): Int {
        return monsters.size
    }
}