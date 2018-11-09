package com.firebase.listas.firebaselistas;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Repaso extends AppCompatActivity {


    FirebaseAuth auth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repaso);

        auth =FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        db.getReference().child("preba").setValue("mar");

        auth.createUserWithEmailAndPassword("maiadmar@gmail.com", "passqord1").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    //logeado
                    String uid = auth.getCurrentUser().getUid();
                    Usuario nuevo_usuario = new Usuario();
                    nuevo_usuario.uid = uid;
                    nuevo_usuario.nombre = "Alejandro";
                    nuevo_usuario.correo = "maiadmar@gmail.com";
                    nuevo_usuario.pass = "1234567";
                    db.getReference().child("usuario").child(uid).setValue(nuevo_usuario);
                }else {
                    Toast.makeText(Repaso.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}