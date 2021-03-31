package pe.edu.tecsup.hablemosdeseguridad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pe.edu.tecsup.hablemosdeseguridad.ui.Historial.HistorialFragment;

public class LoginActivity extends AppCompatActivity {

    private EditText txtDni, txtContrasena;
    private MaterialButton btnIniciar;
    private DatabaseReference reference;
    private String dni,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        reference= FirebaseDatabase.getInstance().getReference("Usuarios");

        txtDni=findViewById(R.id.txtDni);
        txtContrasena=findViewById(R.id.txtContraseña);
        btnIniciar=findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dni=txtDni.getText().toString();
                if (!dni.isEmpty()) {
                    pass = txtContrasena.getText().toString();
                    reference.child(dni).addListenerForSingleValueEvent(listener);
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario no existente", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()){
                String password=snapshot.child("password").getValue(String.class);
                String nombre=snapshot.child("nombres").getValue(String.class);
                if (password.equals(pass)){
                    Intent i =new Intent(LoginActivity.this, BottomNavActivity.class);
                    i.putExtra("dni",dni);
                    startActivity(i);
                    Toast.makeText(LoginActivity.this, "Bienvenido "+nombre, Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(LoginActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
        }
    };
}