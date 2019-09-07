package com.janani123.syndicate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerGrievances extends AppCompatActivity {

   private TextView mobileno,cusname,latt,longt,stats,sum,voic;
   Firebase mobref;
   public String no,cus,lat,longi,stat,summ,voice;
   Button verify;
   DatabaseReference mobrefone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_grievances);
        Firebase.setAndroidContext(this);
        mobref = new Firebase("https://synduser-aa827.firebaseio.com/Output/9790844562");
        mobrefone = FirebaseDatabase.getInstance().getReference();

        mobileno = (TextView)findViewById(R.id.mobileFire);
        cusname = (TextView)findViewById(R.id.nameFire);
        stats = (TextView)findViewById(R.id.StatusFire);
        sum = (TextView)findViewById(R.id.summaryFire);
        voic = (TextView)findViewById(R.id.voiceFire);
        verify = (Button)findViewById(R.id.verify);



        mobref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 no = (String) dataSnapshot.child("Contact").getValue().toString();
                 cus = (String) dataSnapshot.child("Customer").getValue().toString();
                 stat = (String) dataSnapshot.child("Status").getValue().toString();
                 summ = (String) dataSnapshot.child("Summary").getValue().toString();
                 voice = (String) dataSnapshot.child("Voice").getValue().toString();

                mobileno.setText(no);
                cusname.setText(cus);
                stats.setText(stat);
                sum.setText(summ);
                voic.setText(voice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobrefone.child("Output").child("9790844562").child("Status").setValue("Solved");
            }
        });



    }
}
