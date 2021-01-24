package com.example.turistando_android_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText emailLogin, senhaLogin;
    private Button botaoEntrar, irCadastro;
    private FirebaseAuth auth;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarComponentes();
        eventosClicks();
    }

    private void eventosClicks(){
        irCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Cadastro.class);
                startActivity(i);
            }
        });
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailLogin.getText().toString().trim();
                String senha = senhaLogin.getText().toString().trim();
                login(email, senha);
            }
        });
    }

    private void login(String email, String senha){
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(Login.this, Perfil.class);
                            startActivity(i);
                        } else {
                            alert("Falha ao realizar o login");
                        }
                    }
                });
    }

    private void alert(String msg){
        Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void inicializarComponentes(){
        emailLogin = (EditText) findViewById(R.id.emailLogin);
        senhaLogin = (EditText) findViewById(R.id.senhaLogin);
        botaoEntrar = (Button) findViewById(R.id.botaoEntrar);
        irCadastro = (Button) findViewById(R.id.irCadastro);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
