package com.example.bertone.projeto_01;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;


public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


    }

    public void Salvando (View v){

        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        EditText editText4 = (EditText) findViewById(R.id.editText4);

        String texto1 = editText.getText().toString();
        String texto2 = editText2.getText().toString();
        String texto3 = editText3.getText().toString();
        String texto4 = editText4.getText().toString();

        final pessoa  p = new pessoa(texto1,texto2,texto3,texto4);


        new AlertDialog.Builder(this).setTitle("Salvar contato").
                setMessage("Tem certeza que dessaja salvar esse contato ?").
                setPositiveButton("Sim",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        insertContact(p);
                    }
                })
                .setNegativeButton("Cancelar",null)
                .show();
    }


    public void insertContact(pessoa p){

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, p.getNome() + " " + p.getSobreNome());
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, p.getEmail());
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, p.getNumero());
        //startActivity(intent);

        whatsApp(p);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        email(p);

    }


    public void whatsApp(pessoa p){

        try{
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            String url ="http://api.whatsapp.com/send?phone="+ "55"+p.getNumero() +"&text="+URLEncoder.encode("O cadastro foi realizado com sucesso !","UTF-8");
            //sendIntent.putExtra("jid", "55"+ p.getNumero() +"@s.whatsapp.net");
            sendIntent.setPackage("com.whatsapp");
            sendIntent.setData(Uri.parse(url));
            startActivity(sendIntent);
        }
        catch(Exception e)
        {

        }

    }

    public void email(pessoa p) {

        String[] email = {p.getEmail()};
        String message = "Cadastro realizado com sucesso !";
        String assunto = "confirmação de cadastro";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, assunto);
        intent.putExtra(intent.EXTRA_TEXT, message);
        intent.setType("message/rfc822");



        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

/*
    @Override
    protected void onRestart(){
        Intent  voltar = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(voltar);
    }
*/
}
