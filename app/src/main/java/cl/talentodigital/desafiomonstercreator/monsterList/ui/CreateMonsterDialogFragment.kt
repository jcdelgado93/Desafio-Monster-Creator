package cl.talentodigital.desafiomonstercreator.monsterList.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import cl.talentodigital.desafiomonstercreator.databinding.DialogAddMonsterBinding

class CreateMonsterDialogFragment(
    private val createMonsterCallBack: CreateMonsterCallBack
) : DialogFragment() {

    private lateinit var binding: DialogAddMonsterBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            binding = DialogAddMonsterBinding.inflate(LayoutInflater.from(context))
            builder.setView(binding.root)
            builder.setPositiveButton("Guardar") { dialogInterface: DialogInterface, i: Int ->
                createMonsterCallBack.processMonster(
                    binding.etName.text.toString(),
                    binding.etPoints.text.toString(),
                    binding.etAvatar.text.toString()
                )
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}