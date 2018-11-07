package com.agronodo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spark.submitbutton.SubmitButton;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {

    EditText txtCorreo, txtContraseña, txtNombre;
    SubmitButton btnRegistrar;
    static boolean repetido= false;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener listener;
    Task<AuthResult> task = FirebaseAuth.getInstance().signInAnonymously();
    ArrayList<Usuarios> datosUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference(FireBaseReference.REFERENCIAUSUARIO);

        txtNombre = (EditText) findViewById(R.id.registroNombre);
        txtCorreo = (EditText) findViewById(R.id.registroemail);
        txtContraseña = (EditText) findViewById(R.id.registropassword);
        btnRegistrar = (SubmitButton) findViewById(R.id.btnRegistrarUsuario);

        mAuth = FirebaseAuth.getInstance();

        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    Intent i = new Intent(Registro.this, inicioSesion.class);
                    startActivity(i);
                    finish();
                }
            }
        };

        datosUsuarios= new ArrayList<>();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Usuarios usuario= new Usuarios(txtCorreo.getText().toString(), txtContraseña.getText().toString());
                myRef.push().child(FireBaseReference.TABLADATOS).setValue(usuario);
                Toast.makeText(Registro.this, "Se ha agregado eitosamente", Toast.LENGTH_SHORT).show();*/
                try {
                    RegistrarUsuario();
                }catch (Exception e){}
            }

        });
    }

    private void RegistrarUsuario(){
        final String Email= txtCorreo.getText().toString();
        final String Password= txtContraseña.getText().toString();
        String Nombre= txtNombre.getText().toString();
        if(!Email.isEmpty() && !Password.isEmpty() && !Nombre.isEmpty()){
            mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                    } else {

                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                    finish();
                    startActivity(new Intent(Registro.this, inicioSesion.class));
                }
            });
        }
    }
}

