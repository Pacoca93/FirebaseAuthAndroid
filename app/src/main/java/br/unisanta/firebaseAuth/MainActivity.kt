package br.unisanta.firebaseAuth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.firebaseAuth.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btnCriarConta.setOnClickListener {
            var email = binding.edtEmail.text.toString()
            var senha = binding.edtSenha.text.toString()

            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Conta criada!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Falha em criar a conta!", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.btnRedefinirSenha.setOnClickListener {
            var email = binding.edtEmail.text.toString()

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Sucesso em enviar o email!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Falha em enviar o email!", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}