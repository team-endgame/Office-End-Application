package com.janani123.syndicate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.firebase.client.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    private EditText txtEmailLogin;
    private EditText txtpwd;
    private FirebaseAuth firebaseAuth;
    public static String user,pass;
    Firebase nameref,passref;
    CheckBox remember;
    public static int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        txtEmailLogin = findViewById(R.id.textEmailLogin);
        txtpwd = findViewById(R.id.textPasswordLogin);
        remember =(CheckBox) findViewById(R.id.checkbox);
        firebaseAuth = FirebaseAuth.getInstance();

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox =  preferences.getString("remember","");

        if(checkbox.equals("true")){
            flag =1;
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else if(checkbox.equals("false")){
            Toast.makeText(this,"Please Sign in",Toast.LENGTH_SHORT).show();
        }


        String DeviceID = Settings.Secure.getString(getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);
        nameref = new Firebase("https://synduser-aa827.firebaseio.com/"+DeviceID+"/User Name");
        passref = new Firebase("https://synduser-aa827.firebaseio.com/"+DeviceID+"/Password");
        //Retrieving username and deviceID from firebase
        nameref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        passref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pass = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();

                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                }

            }
        });


    }

    public void btnUserLogin_Click(View v) {

        if(user.equalsIgnoreCase(txtEmailLogin.getText().toString())&&pass.equalsIgnoreCase(txtpwd.getText().toString())) {

            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);

        }


        else {
            Toast.makeText(LoginActivity.this,"Error", Toast.LENGTH_SHORT).show();
        }

    }

    public void btnNewUser(View v){
        Intent i = new Intent(LoginActivity.this,RegistrationActivity.class);
        startActivity(i);
    }
}


