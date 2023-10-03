package com.L20290951.myfirstapplication.usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.L20290951.myfirstapplication.R;
import com.L20290951.myfirstapplication.usuario.model.Usuario;
import com.L20290951.myfirstapplication.usuario.DashboardUsuario;
import com.L20290951.myfirstapplication.usuario.reposity.UsuarioRepository;
public class DashboardUsuario extends AppCompatActivity {
    private UsuarioRepository ur;
    private Usuario userInfo;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_usuario);
        //Aquí creamos la instancia del Usuario repository
        ur = UsuarioRepository.getInstance();
        //Esta es la manera en que recibimos información de otro activity
        String usuario = getIntent().getStringExtra("usuario");
        String pass = getIntent().getStringExtra("pass");
        //Obtenemos información del usuario logueado
        userInfo = ur.getRegisteredUsers().get(usuario).get(pass);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tvUserUsuario = findViewById(R.id.tvUserUsuario);
        TextView tvUserNombre = findViewById(R.id.tvUserNombre);
        TextView tvUserEmail = findViewById(R.id.tvUserEmail);
        TextView tvUserEdad = findViewById(R.id.tvUserEdad);
        tvUserUsuario.setText( userInfo.getUsuario() );
        tvUserNombre .setText( userInfo.getNombre() );
        tvUserEmail  .setText( userInfo.getEmail() );
        tvUserEdad   .setText( userInfo.getEdad() + " ".concat( getString(R.string.tvUserEdadComplement) ) );
    }
    private AlertDialog createAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message);
        return builder.create();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tollbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == R.id.miBorrar ){
            Toast.makeText(this, "Borrar", Toast.LENGTH_LONG).show();
            AlertDialog.Builder alerta = new AlertDialog.Builder(DashboardUsuario.this);
            alerta.setMessage("¿Desea eliminar informacion?")
                    .setCancelable(false).setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    Toast.makeText(this, "Se ha logrado con exito", Toast.LENGTH_LONG).show();
                    AlertDialog titulo = alerta.create();
                    titulo.setTitle("Eliminacion");
                    titulo.show();

        } else if ( item.getItemId() == R.id.miInfo
        ) {
            Toast.makeText(this, "Info", Toast.LENGTH_LONG).show();
            this.createAlertDialog("Informacion: ", "Edgar Alejandro Aguila Preciado\nN/C: 20290951").show();
        } else if ( item.getItemId() == R.id.miSetting ) {
            Toast.makeText(this, "Accediendo a configuraciones", Toast.LENGTH_LONG).show();

        }

        return super.onOptionsItemSelected(item);
    }
}