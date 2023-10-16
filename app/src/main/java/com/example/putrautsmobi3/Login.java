package com.example.putrautsmobi3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    // View
    private EditText Username,Password;
    private Button BtnLogin;

    // Java
    private String usernameAdmin = "Admin";
    private String passwordAdmin = "Admin";
    private String usernameUser = "Putra";
    private String passwordUser = "Putra";
    private String tempAdminUsername;
    private String tempAdminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitViews();
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempAdminUsername = Username.getText().toString();
                tempAdminPassword = Password.getText().toString();
                if (usernameAdmin.equals(tempAdminUsername) && passwordAdmin.equals(tempAdminPassword)){
                    Intent intent = new Intent(getApplicationContext(),Pendataan.class);
                    startActivity(intent);
                } else if (usernameUser.equals(tempAdminUsername) && passwordUser.equals(tempAdminPassword)){
                    Intent intent = new Intent(getApplicationContext(),Pendataan.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"Username atau Password salah",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void InitViews(){
        Username = (EditText) findViewById(R.id.UsernameLogin);
        Password = (EditText) findViewById(R.id.PasswordLogin);
        BtnLogin = (Button) findViewById(R.id.ButtonLogin);
    }
}