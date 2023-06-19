package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginactivity.R.id;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal extends AppCompatActivity implements Adapter_Produto.Onclick {

    db banco = new db();
    Button BotaoLogout;

    private List<Produto> produtoList = new ArrayList<>();

    private Adapter_Produto adapter_produto;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        recyclerView = findViewById(id.evProdutos);

        final TextView usu = findViewById(id.usuario);

        String UsuarioLogado;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            UsuarioLogado = extras.getString("usuario_logado");
            usu.setText(UsuarioLogado);
        }

        BotaoLogout = findViewById(R.id.BotaoLogout);
        logaut(BotaoLogout);
        carregaLista();
        configuraRv();


        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.END); // Set swipe direction to end (left-to-right)
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false; // Not used for swipe functionality
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Called when item is swiped
                // Perform desired actions here
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    Drawable icon = ContextCompat.getDrawable(TelaPrincipal.this, R.drawable.baseline_shopping_cart_24);

                    Paint iconPaint = new Paint();

                    int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                    int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                    int iconBottom = iconTop + icon.getIntrinsicHeight();
                    int iconRight = itemView.getLeft() + iconMargin;
                    int iconLeft = iconRight - icon.getIntrinsicWidth();

                    icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                    icon.draw(c);
                }
            }

        });

        helper.attachToRecyclerView(recyclerView);
    }

    protected void logaut(Button BotaoLogout)
    {
        Intent iTelaPrincipal = new Intent(this, MainActivity.class);
        BotaoLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(iTelaPrincipal);
                finish();
            }
        });
    }

    /*
     * Parte que infla o Layout;
     *
     */
    private void carregaLista()
    {
        List<Produto> Itens = new ArrayList<>();
        Itens = banco.pegaTodosOsProdutos();
        Produto produto3 = new Produto();
        for(Produto produto: Itens)
        {
            produto3.setNomeProduto(produto.getNomeProduto());
            produto3.setPrecoProduto((float) 4112.11);
        }

        Produto produto1 = new Produto();
        produto1.setNomeProduto("Monitor LG");
        produto1.setPrecoProduto((float) 4112.11);
        produto1.setQtdEstoqueProduto(90);

        Produto produto2 = new Produto();
        produto2.setNomeProduto("Fone LG");
        produto2.setPrecoProduto((float) 112.11);
        produto2.setQtdEstoqueProduto(9);

        produtoList.add(produto3);
    }

    private void configuraRv()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter_produto = new Adapter_Produto(produtoList, this);
        recyclerView.setAdapter(adapter_produto);
    }


    @Override
    public void onCLickListener(Produto produtinho)
    {
       Toast.makeText(this, "Item :" +String.valueOf(produtinho.getNomeProduto()), Toast.LENGTH_SHORT).show();
    }

}