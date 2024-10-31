package br.edu.fatecpg

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.txvLogin.setOnClickListener{
            auth.signInWithEmailAndPassword("evily@evily.com", "Bilu1234")
                .addOnCompleteListener(this){
                    task -> if(task.isSuccessful){
                    Toast.makeText(this, "Sucesso!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                    Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}