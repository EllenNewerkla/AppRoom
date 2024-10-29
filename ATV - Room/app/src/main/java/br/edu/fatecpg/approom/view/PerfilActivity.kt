package br.edu.fatecpg.approom.view

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
import br.edu.fatecpg.approom.model.User
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: AppDatabase
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()

        userId = intent.getIntExtra("USER_ID", 0)

        lifecycleScope.launch {
            val user = db.userDao().getUserById(userId)
            if (user != null) {
                binding.edtNome.setText(user.firstName)
                binding.edtSobrenome.setText(user.lastName)
                binding.edtEmail.setText(user.email)

                if (!user.profilePictureUri.isNullOrEmpty()) {
                    Glide.with(this@PerfilActivity)
                        .load(user.profilePictureUri)
                        .into(binding.imgProfile) // `ImageView` onde a imagem será exibida
                }
            }
        }

        binding.btnSave.setOnClickListener {
            val updatedUser = User(
                uid = userId,
                firstName = binding.edtNome.text.toString(),
                lastName = binding.edtSobrenome.text.toString(),
                email = binding.edtEmail.text.toString(),
                profilePictureUri = null // Handle image URI if implemented
            )
        }

        lifecycleScope.launch {
            db.userDao().updateUser(updatedUser)
            Toast.makeText(this@PerfilActivity, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show()
        }

        binding.btnDelete.setOnClickListener {
            lifecycleScope.launch {
                db.userDao().deleteUser(updatedUser)
                Toast.makeText(this@PerfilActivity, "Usuário deletado", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
