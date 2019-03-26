package com.example.bertone.projeto_01;

public class pessoa {


    private String nome;
    private String sobreNome;
    private String numero;
    private String email;



    pessoa(String nome, String sobreNome, String numero, String email){
        this.nome = nome ;
        this.sobreNome = sobreNome;
        this.numero = numero;
        this.email = email;
    }//end cantato


    public String getNome(){
        return this.nome;
    }

    public String getSobreNome(){
        return this.sobreNome;
    }

    public String getNumero(){
        return this.numero;
    }

    public String getEmail(){
        return this.email;
    }


}
