package com.example.putrautsmobi3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class Pendataan extends AppCompatActivity{
    // View
    private Button BtnSimpan, ResetBtn, BtnBirthDate;
    private EditText NamaLengkap,NoTelephone, TempatLahir, Alamat, Kecamatan, Kota, Provinsi, Usia, Pekerjaan, Gaji;
    private RadioGroup Status;
    private RadioButton StatusChecked;

    // Data Tempory
    private String namalengkap, notelp, tempatlahir, tanggallahir,alamat, kecamatan, kota, provinsi,usia, pekerjaan, gaji, status;
    private int Year,Month, Day;
    private int SelectId;

    // SharedPreferences
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendataan);
        InitViews();
        BtnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namalengkap = NamaLengkap.getText().toString();
                notelp = NoTelephone.getText().toString();
                tempatlahir = TempatLahir.getText().toString();
                tanggallahir = BtnBirthDate.getText().toString();
                alamat = Alamat.getText().toString();
                kecamatan = Kecamatan.getText().toString();
                kota = Kota.getText().toString();
                provinsi = Provinsi.getText().toString();
                usia = Usia.getText().toString() + " Tahun";
                pekerjaan = Pekerjaan.getText().toString();
                gaji = "Rp. "+ Gaji.getText().toString();
                SelectId = Status.getCheckedRadioButtonId();
                StatusChecked = findViewById(SelectId);
                status = StatusChecked.getText().toString();

                if (namalengkap.isEmpty() && notelp.isEmpty() && tempatlahir.isEmpty() && alamat.isEmpty() && kecamatan.isEmpty() && kota.isEmpty() && provinsi.isEmpty() && usia.isEmpty() && pekerjaan.isEmpty() && gaji.equals("Rp. ")){
                    Toast.makeText(getApplicationContext(), "Tolong isi semua formulir", Toast.LENGTH_LONG).show();
                } else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("DataNama", namalengkap);
                    editor.putString("DataTelp", notelp);
                    editor.putString("DataTempatLahir", tempatlahir);
                    editor.putString("DataTanggalLahir", tanggallahir);
                    editor.putString("DataAlamat", alamat);
                    editor.putString("DataKecamatan", kecamatan);
                    editor.putString("DataKota", kota);
                    editor.putString("DataProvinsi", provinsi);
                    editor.putString("DataUsia", usia);
                    editor.putString("DataPekerjaan", pekerjaan);
                    editor.putString("DataGaji", gaji);
                    editor.putString("DataStatus", status);
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(),TampilData.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Data Berhasil Tersimpan",Toast.LENGTH_LONG).show();
                }
            }
        });


        BtnBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopDatePicker(v);
            }
        });

        ResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NamaLengkap.setText("");
                NoTelephone.setText("");
                TempatLahir.setText("");
                Alamat.setText("");
                Kecamatan.setText("");
                Kota.setText("");
                Provinsi.setText("");
                Usia.setText("");
                Pekerjaan.setText("");
                Gaji.setText("");
                BtnBirthDate.setText("Tanggal Lahir");
                Status.clearCheck();
            }
        });
    }

    private void InitViews(){
        NamaLengkap = (EditText) findViewById(R.id.InputNamaLengkap);
        NoTelephone = (EditText) findViewById(R.id.InputNoTelephone);
        TempatLahir = (EditText) findViewById(R.id.InputTempatLahir);
        Alamat = (EditText) findViewById(R.id.InputAlamat);
        Kecamatan = (EditText) findViewById(R.id.InputKecamatan);
        Kota = (EditText) findViewById(R.id.InputKota);
        Provinsi = (EditText) findViewById(R.id.InputProvinsi);
        Usia = (EditText) findViewById(R.id.InputUsia);
        Pekerjaan = (EditText) findViewById(R.id.InputPekerjaan);
        Gaji = (EditText) findViewById(R.id.InputGaji);
        BtnSimpan = (Button) findViewById(R.id.BtnSaveData);
        ResetBtn = (Button) findViewById(R.id.ResetData);
        BtnBirthDate = (Button) findViewById(R.id.BirthDate);
        Status = (RadioGroup) findViewById(R.id.InputStatus);

        preferences = getSharedPreferences("Data",MODE_PRIVATE);
    }

    private void PopDatePicker(View view){
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                tanggallahir = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                BtnBirthDate.setText(tanggallahir);
                calendar.set(Calendar.YEAR, Year);
                calendar.set(Calendar.MONTH, Month);
                calendar.set(Calendar.DAY_OF_MONTH, Day);
            }
        };
        int Style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,Style,onDateSetListener,Year,Month,Day);
        datePickerDialog.getDatePicker().setMinDate((System.currentTimeMillis())/ 1000 / 60 / 60 / 24 / 365);
        datePickerDialog.setTitle("Pick Your Birth Day");
        datePickerDialog.show();
    }
}