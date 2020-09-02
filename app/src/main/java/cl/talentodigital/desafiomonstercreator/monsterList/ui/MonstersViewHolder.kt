package cl.talentodigital.desafiomonstercreator.monsterList.ui

import androidx.recyclerview.widget.RecyclerView
import cl.talentodigital.desafiomonstercreator.databinding.ItemMonsterBinding
import cl.talentodigital.desafiomonstercreator.monsterList.domain.model.Monster
import com.squareup.picasso.Picasso

class MonstersViewHolder(
    private val binding : ItemMonsterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(monster: Monster) {
        binding.apply {
            tvName.text = monster.name
            tvMonsterPoints.text = monster.monsterPoints
            Picasso.get().load(monster.image).into(ivAvatar)
        }
    }
}