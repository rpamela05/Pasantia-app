package pe.edu.tecsup.hablemosdeseguridad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import pe.edu.tecsup.hablemosdeseguridad.ui.Encuesta.EncuestaFragment;

public class BottomNavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Intent intent=getIntent();
        String dni=intent.getStringExtra("dni");
        Bundle bundle=new Bundle();
        bundle.putString("name","aaaaaaaa");
        EncuestaFragment effacement=new EncuestaFragment();
        effacement.setArguments(bundle);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        getSupportActionBar().hide();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.historialFragment, R.id.encuestaFragment, R.id.configFragment)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

    }

}