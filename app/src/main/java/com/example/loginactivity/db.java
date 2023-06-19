package com.example.loginactivity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.Formattable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class db {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth loga;

    Map doc =new HashMap();

    public void consulta()
    {
        db.collection("usuarios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document: task.getResult())
                    {
                        doc = document.getData();
                        Log.d(TAG, document.getId() +" -> "+document.getData());
                        String tipo = document.getData().getClass().toString();
                        Log.d(TAG, doc.toString());
                    }
                }
            }
        });
    }

    public void busca_email(String email)
    {
        db.collection("usuarios").whereEqualTo("email", email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {

            if(task.isSuccessful())
            {
                for(QueryDocumentSnapshot document: task.getResult())
                {
                    doc = document.getData();
                    Log.d(TAG, document.getId() +" -> "+document.getData());
                    String tipo = document.getData().getClass().toString();
                    Log.d(TAG, doc.toString());
                }
            }
        }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Tratar erros de consulta
                System.out.printf("\n\n\n  ******** NÃO D3U ***********  \n\n\n\n");
            }
        });
    }

    protected FirebaseUser autentifica_login(String email, String senha)
    {
        loga = FirebaseAuth.getInstance();
        loga.signInWithEmailAndPassword(email, senha).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    System.out.printf("\n\n\n  ******** LOGADO ***********  \n\n\n\n");
                }
                else
                {
                    System.out.printf("\n\n\n  ********NÃO LOGADO ***********  \n\n\n\n");
                }
            }
        });
        return loga.getCurrentUser();
    }

    protected String retorna_usuario_logado()
    {
        FirebaseUser usuario_logado = FirebaseAuth.getInstance().getCurrentUser();
        if (usuario_logado != null)
        {
            return usuario_logado.getEmail().toString();
        }
        else
        {
           return "";
        }
    }

    protected List  pegaTodosOsProdutos()
    {
        List<Produto> Itens = new ArrayList<>();

        db.collection("produtos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    Itens.clear();
                    for(QueryDocumentSnapshot document: task.getResult())
                    {

                        //produto.setNomeProduto(document.getString("nome"));
                        //produto.setPrecoProduto(document.getDouble("preco").floatValue());
                        //produto.setQtdEstoqueProduto(document.getLong("quantidade").intValue());

                        String nome = document.getString("nome");
                        Float preco = document.getDouble("preco").floatValue();
                        Produto produto = new Produto();
                        produto.setNomeProduto(nome);
                        produto.setPrecoProduto(preco);
                        Itens.add(produto);
                        //{preco=1.99, categoria=geral, nome=Filme 1, quantidade=10, descricao=filme bom}
                    }

                    //for( Produto produto: Itens)
                    //{
                     //   System.out.printf("\n\n %s \n\n", produto.getNomeProduto());
                    //}
                }
            }
        });
        return Itens;
    }









}
