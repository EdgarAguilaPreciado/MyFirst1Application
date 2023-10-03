package com.L20290951.myfirstapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.L20290951.myfirstapplication.usuario.DashboardUsuario;
import com.L20290951.myfirstapplication.usuario.model.Usuario;
import com.L20290951.myfirstapplication.usuario.DashboardUsuario;
import com.L20290951.myfirstapplication.usuario.reposity.UsuarioRepository;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private UsuarioRepository ur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ur = UsuarioRepository.getInstance();
    }
    private AlertDialog createAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message);
        return builder.create();
    }
    public void onBtnIngresarTap(View view) {
        EditText etUsuario = findViewById(R.id.etUsuario);
        EditText etPass    = findViewById(R.id.etPass);

        String user, pass;
        user = etUsuario.getText().toString();
        pass = etPass.getText().toString();

        if ( !ur.getRegisteredUsers().containsKey(user) ) {
            this.createAlertDialog("Atención", "Usuario no registrado").show();
            return;
        }

        if ( !ur.getRegisteredUsers().get(user).containsKey(pass) ){
            this.createAlertDialog("Atención", "La contraseña no coincide").show();
            return;
        }

        String message = "Felicidades ha iniciado sesión con éxito";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, DashboardUsuario.class);
        i.putExtra("usuario", user);
        i.putExtra("pass", pass);
        startActivity(i);
    }
}