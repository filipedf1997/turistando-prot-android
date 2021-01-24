package com.example.turistando_android_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil  extends AppCompatActivity {
    private TextView emailLogado, idLogado;
    private Button botaoSair;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_perfil);
        inicializarComponentes();
        eventosClicks();
    }

    private void eventosClicks(){
        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexao.logout();
                finish();
            }
        });
    }

    private void inicializarComponentes(){
        emailLogado = (TextView) findViewById(R.id.emailLogado);
        idLogado = (TextView) findViewById(R.id.idLogado);
        botaoSair = (Button) findViewById(R.id.botaoSair);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificaUser();
    }

    private void verificaUser(){
        if(user == null){
            finish();
        } else {
            emailLogado.setText("Email: " + user.getEmail());
            idLogado.setText("ID: " + user.getUid());
        }
    }
}
