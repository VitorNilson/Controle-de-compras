package com.code.bang.plannercompras.raiz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.code.bang.plannercompras.raiz.dao.DataAcessObject;
import com.code.bang.plannercompras.raiz.item.Item;
import com.code.bang.plannercompras.R;

public class AdicionarItem extends AppCompatActivity {

    final static DataAcessObject dao = new DataAcessObject();
    private Item recebeItem;
    private EditText campoItem;
    private EditText campoQuantidade;
    private EditText campoValorUnidade;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_item);

        inicializaCampos();
        configuraBotaoAdiciona();
        recebeItemSerializado();


    }

    private void configuraBotaoAdiciona() {
        Button botaoAdiciona = findViewById(R.id.activity_adicionar_item_botao_adicionar);
        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setaDadosItem();
                if(recebeItem.idEValido()){
                    dao.editaItem(recebeItem);
                }else{
                    dao.adiciona(recebeItem);
                }

                Toast.makeText(AdicionarItem.this, recebeItem.getValorTotal().toString(), Toast.LENGTH_SHORT).show();

                Intent mudaDeActivity = new Intent(AdicionarItem.this, MainActivity.class);
                finish();
                startActivity(mudaDeActivity);
            }
        });
    }

    public void setaDadosItem(){
        String item = (campoItem.getText().toString());
        Integer quantidade = (Integer.parseInt(campoQuantidade.getText().toString()));
        Double valorUnidade = (Double.parseDouble(campoValorUnidade.getText().toString()));

        recebeItem.setItem(item);
        recebeItem.setQuantidade(quantidade);
        recebeItem.setValorItem(valorUnidade);
        recebeItem.setValorTotal(valorUnidade, quantidade);
    }

    public void recebeItemSerializado(){

        Intent dadosTelaMain = getIntent();
        if(dadosTelaMain.hasExtra("Item")) {
            recebeItem = (Item) dadosTelaMain.getSerializableExtra("Item");
            campoItem.setText(recebeItem.getItem());
            campoQuantidade.setText(Integer.toString(recebeItem.getQuantidade()));
            campoValorUnidade.setText(Double.toString(recebeItem.getValorItem()));
        }else{
            recebeItem = new Item();
        }
    }


    private void inicializaCampos() {
        campoItem =  findViewById(R.id.activity_adicionar_item_item);
        campoQuantidade = findViewById(R.id.activity_adicionar_item_quantidade);
        campoValorUnidade = findViewById(R.id.activity_adicionar_item_valor);
    }


}