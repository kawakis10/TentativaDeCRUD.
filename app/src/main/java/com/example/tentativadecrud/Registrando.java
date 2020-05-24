package com.example.tentativadecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.PrivilegedAction;

public class Registrando extends AppCompatActivity {

    private EditText mEditEmail;
    private EditText mEditPassword;
    private Button mEnter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrando);

        mEditEmail = findViewById(R.id.editEmail);
        mEditPassword = findViewById(R.id.editPassword);
        mEnter = findViewById(R.id.btnLogin);

        mEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });




    }

    private void createUser(){
        String email = mEditEmail.getText().toString();
        String senha = mEditPassword.getText().toString();
        if(senha ==null || senha.isEmpty() || email.isEmpty() || email == null){
            Log.i("testefeito", "Falta email e/ou senha");
            //Toast.makeText(this, "Senha e emails devem ser preenchidos", Toast.LENGTH_SHORT);
            return;
        }else {
            Intent intent = new Intent(Registrando.this, MainActivity.class);

            startActivity(intent);
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.i("teste", task.getResult().getUser().getUid());

                    Intent intent = new Intent(Registrando.this, MainActivity.class);
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("teste", e.getMessage());
                    }
                });
    }


}
