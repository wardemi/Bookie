package com.visualstudio.verboben14.bookie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Intent goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToLogin = new Intent(this, LoginActivity.class);

        final FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            startActivity(goToLogin);
        } else {
            String email = user.getEmail();

            Toast.makeText(MainActivity.this, "Üdv "+email.toString(),
                    Toast.LENGTH_SHORT).show();
        }

        Button signOutBtn = (Button) findViewById(R.id.singOutBtn);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Kiléps ",
                        Toast.LENGTH_SHORT).show();

                startActivity(goToLogin);
            }
        });
    }

}
