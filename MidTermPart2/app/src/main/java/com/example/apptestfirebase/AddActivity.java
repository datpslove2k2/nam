package com.example.apptestfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
     EditText namekh, nametg, dactinh, maula, congdung, duoctinh, chuy, turl;
     Button btnAdd, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        namekh = (EditText) findViewById(R.id.txtnamekh);
        nametg = (EditText) findViewById(R.id.txtnametg);
        dactinh = (EditText) findViewById(R.id.txtdactinh);
        maula = (EditText) findViewById(R.id.txtmaula);
        congdung = (EditText) findViewById(R.id.txtcongdung);
        duoctinh = (EditText) findViewById(R.id.txtmaula);
        chuy = (EditText) findViewById(R.id.txtchuy);
        turl = (EditText) findViewById(R.id.txtImageUrl);

        btnAdd = (Button) findViewById(R.id.btn_Add);
        btnBack = (Button) findViewById(R.id.btn_Back);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void insertData() {
        Map<String,Object> map = new HashMap<>();
        map.put("nameKH", namekh.getText().toString());
        map.put("nameTG", nametg.getText().toString());
        map.put("dacTinh", dactinh.getText().toString());
        map.put("mauLa", maula.getText().toString());
        map.put("congDung", congdung.getText().toString());
        map.put("duocTinh", duoctinh.getText().toString());
        map.put("chuY", chuy.getText().toString());
        map.put("turl", turl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("teachers").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data Inserted Successfully!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(AddActivity.this, "Error while Insertion!!", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}