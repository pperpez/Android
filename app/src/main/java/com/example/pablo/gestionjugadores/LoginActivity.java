package com.example.pablo.gestionjugadores;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuario, edtPassword;
    private Button btnLogin;
    private CheckBox chkRemember;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        chkRemember = (CheckBox)findViewById(R.id.chkRemember);
        sp = getSharedPreferences("datos", MODE_PRIVATE);
        if(sp.contains("user")){
            Intent i = new Intent(LoginActivity.this, ListaActivity.class);
            startActivity(i);
            finish();
        }
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtUsuario.getText().toString().matches("") && !edtPassword.getText().toString().matches("")) {
                    if (chkRemember.isChecked()) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("user", edtUsuario.getText().toString());
                        editor.putString("password", edtPassword.getText().toString());
                        editor.commit();
                    }
                    edtUsuario.setText("");
                    edtPassword.setText("");
                    Intent i = new Intent(LoginActivity.this, ListaActivity.class);
                    startActivity(i);
                    finish();
                } else{
                    Toast.makeText(LoginActivity.this, R.string.mensaje, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
