package cl.talentodigital.desafiomonstercreator.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.alert(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}