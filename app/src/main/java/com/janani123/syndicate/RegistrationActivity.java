package com.janani123.syndicate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText txtEmailAddress;
    private EditText txtPassword;
    private EditText name;
    private EditText mobile;
    private EditText clgname;
    private EditText username;
    private FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;
    Firebase mRef;
    String fname,mobno,mailid,passw,clg,uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        txtEmailAddress = findViewById(R.id.textEmailLogin);
        txtPassword = findViewById(R.id.textPasswordLogin);
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.Conactnum);
        clgname = findViewById(R.id.College);
        username = findViewById(R.id.Username);




        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Accounts");
    }

    public void btnRegistration(View v){

        if(name.getText().toString().isEmpty()||mobile.getText().toString().isEmpty()||txtEmailAddress.getText().toString().isEmpty()||
                clgname.getText().toString().isEmpty()||username.getText().toString().isEmpty()||txtPassword.getText().toString().isEmpty())
        {
            Toast.makeText(RegistrationActivity.this,"Above fields are mandatory",Toast.LENGTH_SHORT).show();
        }

        //storing field values in firebase
        String DeviceID = Settings.Secure.getString(getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);
        mRef = new Firebase("https://synduser-aa827.firebaseio.com/"+DeviceID);
        mRef.child("Name").setValue(name.getText().toString());
        mRef.child("Mobile Number").setValue(mobile.getText().toString());
        mRef.child("Mail ID").setValue(txtEmailAddress.getText().toString());
        mRef.child("Branch Name").setValue(clgname.getText().toString());
        mRef.child("User Name").setValue(username.getText().toString());
        mRef.child("Password").setValue(txtPassword.getText().toString());

        // authenticating email and password
        final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, "Please wait...", "Processing...", true);
        (firebaseAuth.createUserWithEmailAndPassword(txtEmailAddress.getText().toString(), txtPassword.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(i);
                        }

                        else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}

