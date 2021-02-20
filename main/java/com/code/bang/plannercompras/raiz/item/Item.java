package com.code.bang.plannercompras.raiz.item;

import java.io.Serializable;

public class Item implements Serializable {

    private String item;
    private Integer quantidade;
    private Double valorItem;
    private static Double valorTotal = 0.0;
    private static Double valorTotalItem = 0.0;
    private Integer id = 0;

    public Item() {
    }

    public Item(String i, Integer q, Double v) {
        this.item = i;
        this.quantidade = q;
        this.valorItem = v;
    }


    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public void setValorItem(Double valorItem) {
        this.valorItem = valorItem;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorItem, Integer quantidade) {
        this.valorTotal = valorTotal + (valorItem * quantidade);
    }

    public boolean idEValido(){
        if(id > 0){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public String toString() {
        return item + " - Quantidade: " + quantidade + " Valor Unidade: R$ " + valorItem + "\nTotal: R$ " + quantidade * valorItem ;
    }

    public void subtraiValor() {

        this.valorTotal = this.valorTotal - (this.valorItem * this.quantidade) ;
    }
}
