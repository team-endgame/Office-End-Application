package com.janani123.syndicate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    public Button mapactivity,knowcus,cusgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapactivity = (Button)findViewById(R.id.map);
        knowcus = (Button)findViewById(R.id.kyc);
        cusgr = (Button)findViewById(R.id.cusgrie);

        mapactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

        knowcus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,KYC.class);
                startActivity(intent);
            }
        });

        cusgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomerGrievances.class);
                startActivity(intent);
            }
        });




    }


}



