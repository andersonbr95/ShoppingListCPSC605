package com.example.softwareengineeringfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 10005;
    SignInButton signIn;
    GoogleSignInOptions gso;
    GoogleSignInClient signInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        signIn = findViewById(R.id.signInButton);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        signInClient = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            startActivity(new Intent(this, MapsActivity.class));

        }

        signIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent sign = signInClient.getSignInIntent();
                startActivityForResult(sign, REQUEST_CODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount signInAccount = signInAccountTask.getResult(ApiException.class);
                Toast.makeText(this, "Signed in Successfully with your Google Account", Toast.LENGTH_SHORT);
                startActivity(new Intent (this, DashboardActivity.class));
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}