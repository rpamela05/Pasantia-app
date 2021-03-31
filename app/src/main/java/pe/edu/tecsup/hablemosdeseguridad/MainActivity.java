package pe.edu.tecsup.hablemosdeseguridad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN=5000;

    Animation arriba, abajo;
    ImageView logo;
    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        arriba=AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        abajo=AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);

        logo=findViewById(R.id.logo);
        text1=findViewById(R.id.deTextView);
        text2=findViewById(R.id.pacasmayoTextView);

        logo.setAnimation(arriba);
        text1.setAnimation(abajo);
        text2.setAnimation(abajo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}