package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RecuperaSenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_senha);

        FloatingActionButton b_voltar = findViewById(R.id.voltar);
        acaoVoltar(b_voltar);
    }


    protected void acaoVoltar(FloatingActionButton BotaoVoltar)
    {
        BotaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iTelaPrincipal = new Intent(RecuperaSenha.this,MainActivity.class);
                startActivity(iTelaPrincipal);
                finish();
            }
        });
    }
}