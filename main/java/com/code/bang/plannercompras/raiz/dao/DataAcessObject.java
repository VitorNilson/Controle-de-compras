    package com.code.bang.plannercompras.raiz.dao;

import com.code.bang.plannercompras.raiz.item.Item;

import java.util.ArrayList;
import java.util.List;

public class DataAcessObject {

   private static Integer contadorId = 1;
   private static final List<Item> listaDeItens = new ArrayList<>();

    public void adiciona(Item item){
        item.setId(contadorId);
        listaDeItens.add(item);
        contadorId++;
    }

    public void editaItem(Item itemRecebido){
        Item itemEncontrado = null;
        for (Item i: listaDeItens) {
            if(i.getId() == itemRecebido.getId()){
                itemEncontrado = i;
            }
        }
        if(itemEncontrado != null){
            Integer posicaoItem = listaDeItens.indexOf(itemEncontrado);
              listaDeItens.set(posicaoItem, itemRecebido);
        }
    }



    public List<Item> exibirTodosItens(){
        return new ArrayList<>(listaDeItens);
    }

    public void removeItem(Item posicao) {
        for (Item i: listaDeItens) {
            if(i.getId() == posicao.getId()){
                posicao.subtraiValor();
                listaDeItens.remove(posicao);
            }
        }
    }
}
