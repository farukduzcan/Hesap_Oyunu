package com.example.hesap_oyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText editText1,editText2,editText3,editText4;
    ListView listView1,listView2;
    String[] islemler={"+","-","*","/"};
    Random rastgele=new Random();
    int islem,sonuc,dogrusayi=0,yanlissayi=0;
    String secilenislem,secilensonuc;
    ArrayList<String> dogru=new ArrayList<>();
    ArrayList<String> yanlis=new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);
        editText4=findViewById(R.id.editText4);
        listView1=findViewById(R.id.listView1);
        listView2=findViewById(R.id.listView2);
        editText2.setEnabled(false);
        editText4.setEnabled(false);
        sonuc=rastgele.nextInt(100);
        islem=rastgele.nextInt(4);
        secilenislem=islemler[islem];
        editText2.setText(secilenislem);
        secilensonuc= String.valueOf(sonuc);
        editText4.setText(secilensonuc);
        editText1.setText(String.valueOf(0));
        editText3.setText(String.valueOf(0));

    }


    public void kontrol_et(View view) {

        int girilensonuc = 0;

        if(islemler[islem]=="+"){
            girilensonuc= Integer.parseInt(editText1.getText().toString()) +Integer.parseInt(editText3.getText().toString());
        }
        if(islemler[islem]=="-"){
            girilensonuc= Integer.parseInt(editText1.getText().toString()) -Integer.parseInt(editText3.getText().toString());
        }
        if(islemler[islem]=="*"){
            girilensonuc= Integer.parseInt(editText1.getText().toString()) *Integer.parseInt(editText3.getText().toString());
        }
        if(islemler[islem]=="/"){
            girilensonuc= Integer.parseInt(editText1.getText().toString()) /Integer.parseInt(editText3.getText().toString());
        }

        if(girilensonuc==sonuc){
            //doğru
            dogrusayi++;
            Toast.makeText(this, "Girilen Doğru Cevaplar: "+dogrusayi, Toast.LENGTH_SHORT).show();
            dogru.add(Integer.parseInt(editText1.getText().toString())+islemler[islem]+Integer.parseInt(editText3.getText().toString())+" = "+sonuc);
            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, dogru);
            listView1.setAdapter(adapter);

        }
        if(girilensonuc!=sonuc){
            yanlissayi++;
            Toast.makeText(this, "Girilen Yanlış Cevaplar: "+yanlissayi, Toast.LENGTH_SHORT).show();

            yanlis.add(Integer.parseInt(editText1.getText().toString())+islemler[islem]+Integer.parseInt(editText3.getText().toString())+" != "+sonuc);
            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, yanlis);
            listView2.setAdapter(adapter);
        }
        if(dogrusayi==10||yanlissayi==5){
            Toast.makeText(this, "Doğru veya Yanlış sayı hakkınız doldu", Toast.LENGTH_SHORT).show();
            dogru.clear();
            yanlis.clear();
            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, dogru);
            listView1.setAdapter(adapter);
            adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, yanlis);
            listView2.setAdapter(adapter);
            dogrusayi=0;
            yanlissayi=0;
        }

        sonuc=rastgele.nextInt(100);
        islem=rastgele.nextInt(4);
        secilenislem=islemler[islem];
        editText2.setText(secilenislem);
        secilensonuc= String.valueOf(sonuc);
        editText4.setText(secilensonuc);
        editText1.setText(String.valueOf(0));
        editText3.setText(String.valueOf(0));

    }
}