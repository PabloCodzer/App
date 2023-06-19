package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    db banco= new db();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Botão de logar;
        TextInputLayout SenhaLayout = findViewById(R.id.LayoutSenhaCadastro);
        TextInputEditText Senha = findViewById(R.id.cadastroSenha);

        TextView cadastro = findViewById(R.id.cadastrar);
        cadastrar(cadastro);
        TextView recuperarSenha = findViewById(R.id.recupearsenha);
        recuperaSenha(recuperarSenha);

        TextInputLayout emailLayout = findViewById(R.id.LayoutEmailCadastro);
        TextInputEditText Email = findViewById(R.id.cadastroEmail);

        Button Botao_proxima = findViewById(R.id.BotaoLogar);
        validaLogin(Botao_proxima, Senha, SenhaLayout, Email,emailLayout);

        String usuario = banco.retorna_usuario_logado().toString();

        if(!usuario.isEmpty()){
            usuario_ja_logado(usuario);
        }
    }

    public void validaLogin(Button botao, TextInputEditText senha, TextInputLayout SenhaLayout, TextInputEditText email, TextInputLayout emailLayout)
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
                    valida_login(email.getText().toString() ,senha.getText().toString());
                }
            }
        } );

    }

    protected void valida_login(String email, String senha)
    {
        FirebaseUser reultLogIn;
        reultLogIn = banco.autentifica_login(email, senha );

        Intent iTelaPrincipal = new Intent(this, TelaPrincipal.class);
        Bundle bundleUsuario = new Bundle();

        if(reultLogIn != null)
        {
            Toast.makeText(getApplicationContext(), "Logado: "+reultLogIn.getEmail(),
                    Toast.LENGTH_SHORT).show();
            bundleUsuario.putString("usuario_logado",reultLogIn.getEmail());
            iTelaPrincipal.putExtras(bundleUsuario);
            startActivity(iTelaPrincipal);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Erro de Login :", Toast.LENGTH_SHORT).show();
        }
    }

    protected void usuario_ja_logado(String email)
    {
        Intent iTelaPrincipal = new Intent(this, TelaPrincipal.class);
        Bundle bundleUsuario = new Bundle();

        bundleUsuario.putString("usuario_logado",email);
        iTelaPrincipal.putExtras(bundleUsuario);
        startActivity(iTelaPrincipal);
        finish();
    }

    protected void recuperaSenha(TextView recuperarSenha)
    {
        Intent recuperaSenhaView = new Intent(this, RecuperaSenha.class);
        recuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(recuperaSenhaView);
                finish();
            }
        });
    }

    protected void cadastrar(TextView cadastro)
    {
        Intent Novocadastro = new Intent(this, CadastroNovoUsuario.class);
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Novocadastro);
                finish();
            }
        });
    }
}
