package com.example.pablo.gestionjugadores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView imagenCargando = (ImageView)findViewById(R.id.imagenCargando);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        //final Animation animation2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_face_out);

        imagenCargando.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
