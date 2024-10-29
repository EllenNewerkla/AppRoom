package br.edu.fatecpg.approom.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import br.edu.fatecpg.approom.R
import br.edu.fatecpg.approom.database.AppDatabase
import br.edu.fatecpg.approom.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()

            lifecycleScope.launch {
                val user = db.userDao().getUserByEmail(email)
                if (user != null) {
                    val intent = Intent(this@LoginActivity, PerfilActivity::class.java)
                    intent.putExtra("User_ID", user.uid)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Usuario não encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}