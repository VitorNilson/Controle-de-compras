package com.code.bang.plannercompras.raiz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.code.bang.plannercompras.raiz.item.Item;
import com.code.bang.plannercompras.R;
import com.code.bang.plannercompras.raiz.dao.DataAcessObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final static DataAcessObject dao = new DataAcessObject();
    Item exibeValor = new Item();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Compras -" + " Valor total R$ " + exibeValor.getValorTotal());
        configuraListenerFAB();

    }

    private void configuraListenerFAB() {
        FloatingActionButton fab = findViewById(R.id.activity_lista_fab_adicao);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mudaDeActivity = new Intent(MainActivity.this, AdicionarItem.class);
                finish();
                startActivity(mudaDeActivity);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // exibe item inputado
        ListView listView = findViewById(R.id.acitivity_listview_main);
        List<Item> exibirListaItens = dao.exibirTodosItens();

        configuraListenerListView(listView, exibirListaItens);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "long click", Toast.LENGTH_SHORT).show();
                Item itemSelecionado = exibirListaItens.get(position);
                dao.removeItem(itemSelecionado);
                finish();
                startActivity(getIntent());
                return true;
            }
        });


    }

    private void configuraListenerListView(ListView listView, List<Item> exibirListaItens) {
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exibirListaItens));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Item itemSelecionado = exibirListaItens.get(position);
                //Toast.makeText(MainActivity.this, position, Toast.LENGTH_SHORT).show();

                Intent mudaTela = new Intent(MainActivity.this, AdicionarItem.class);
                mudaTela.putExtra("Item", itemSelecionado);
                finish();
                startActivity(mudaTela);
            }
        });
    }
}