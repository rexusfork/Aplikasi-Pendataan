package com.example.putrautsmobi3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TampilData extends AppCompatActivity {
    private Button backBtn;
    private ListView view;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data);
        InitViews();
        preferences = getSharedPreferences("Data", MODE_PRIVATE);
        String[] listdata = {
                preferences.getString("DataNama","Default"),
                preferences.getString("DataUsia","Default"),
                preferences.getString("DataTelp","Default"),
                preferences.getString("DataTempatLahir","Default"),
                preferences.getString("DataTanggalLahir","Default"),
                preferences.getString("DataAlamat","Default"),
                preferences.getString("DataKecamatan","Default"),
                preferences.getString("DataKota","Default"),
                preferences.getString("DataProvinsi","Default"),
                preferences.getString("DataPekerjaan","Default"),
                preferences.getString("DataGaji","Default"),
                preferences.getString("DataStatus","Default"),
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,listdata);
        view.setAdapter(adapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Pendataan.class);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                startActivity(intent);
            }
        });
    }

    private void InitViews(){
        backBtn = (Button) findViewById(R.id.BackBtn);
        view = (ListView) findViewById(R.id.ListView);
    }
}