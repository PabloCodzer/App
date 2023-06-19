package com.example.loginactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_Produto extends RecyclerView.Adapter<Adapter_Produto.ProdutoViewHolder> {

    private List<Produto> produtoList;
    private Onclick onclick;

    public Adapter_Produto(List<Produto> produtoList, Onclick onclick)
    {
        this.produtoList = produtoList;
        this.onclick = onclick;
    }

    class ProdutoViewHolder extends RecyclerView.ViewHolder
    {
        TextView nomeProdutoCard, estoqueProdutoCard, valorProdutoCard;
        //ImageView imagemProduto;
        public ProdutoViewHolder(@NonNull View itemView)
        {
            super(itemView);
            nomeProdutoCard = itemView.findViewById(R.id.nomeProdutoCard);
            estoqueProdutoCard = itemView.findViewById(R.id.estoqueProdutoCard);
            valorProdutoCard = itemView.findViewById(R.id.valorProdutoCard);
        }
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemDoCardProduto = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_produto,parent,false);
        return new ProdutoViewHolder(itemDoCardProduto);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position)
    {
        Produto produto = produtoList.get(position);
        holder.nomeProdutoCard.setText(produto.getNomeProduto());
        holder.estoqueProdutoCard.setText("Disponivel: "+String.valueOf( produto.getQtdEstoqueProduto() ));
        holder.valorProdutoCard.setText("R$: "+String.valueOf( produto.getPrecoProduto()));
        holder.itemView.setOnClickListener(view -> onclick.onCLickListener(produto));
    }

    @Override
    public int getItemCount()
    {
        return produtoList.size();
    }

    public interface Onclick
    {
        void onCLickListener(Produto produtinho);
    }

}
