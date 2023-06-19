package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroNovoUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_novo_usuario);

        FloatingActionButton b_voltar = findViewById(R.id.voltar);
        acaoVoltar(b_voltar);

        TextInputLayout senhalLayout = findViewById(R.id.LayoutSenhaCadastro);
        TextInputEditText cadastroSenha = findViewById(R.id.cadastroSenha);

        TextInputLayout emailLayout = findViewById(R.id.LayoutEmailCadastro);
        TextInputEditText cadastroEmail = findViewById(R.id.cadastroEmail);
        Button b_cadastrar = findViewById(R.id.botaoCadastra);
        validaCadastro(b_cadastrar, cadastroSenha, senhalLayout, cadastroEmail, emailLayout);

    }

    protected void acaoVoltar(FloatingActionButton BotaoVoltar)
    {
        BotaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iTelaPrincipal = new Intent(CadastroNovoUsuario.this,MainActivity.class);
                startActivity(iTelaPrincipal);
                finish();
            }
        });
    }

    protected void validaCadastro(Button botao, TextInputEditText senha, TextInputLayout SenhaLayout, TextInputEditText email, TextInputLayout emailLayout)
    {
        botao.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SenhaLayout.setError("");
                emailLayout.setError("");

                if(senha.getText().toString().isEmpty() && email.getText().toString().isEmpty())
                {
                    SenhaLayout.setError("Insira uma senha !!! ");
                    emailLayout.setError("Insira um Email !!!");
                }
                else if (senha.getText().toString().isEmpty() )
                {
                    SenhaLayout.setError("Insira uma senha !!! ");
                }
                else if(email.getText().toString().isEmpty())
                {
                    emailLayout.setError("Insira um Email !!!");
                }
                else
                {
                    //valida_login(email.getText().toString() ,senha.getText().toString());
                    Toast.makeText(getApplicationContext(), "Login Disponível", Toast.LENGTH_SHORT).show();
                }
            }
        } );
    }






}