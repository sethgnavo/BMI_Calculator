package com.align.calculdelimc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageView imageView = (ImageView) findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim3);
        imageView.startAnimation(animation);
        TextView txtAppAuthor = (TextView) findViewById(R.id.txt_app_author);
        txtAppAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://www.facebook.com/sgnavogollar");
                i.setData(uri);
                startActivity(i);
            }
        });
    }

}