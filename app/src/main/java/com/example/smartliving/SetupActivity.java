package com.example.smartliving;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private EditText UserName, FullName, County;
    private Button SaveButton;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference userRef;

    String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUserID = firebaseAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);

        UserName = (EditText) findViewById(R.id.setup_username);
        FullName = (EditText) findViewById(R.id.setup_fullname);
        County = (EditText) findViewById(R.id.setup_county);
        SaveButton = (Button) findViewById(R.id.save_button);
        progressDialog = new ProgressDialog(this);

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAccountSetupInformation();
            }
        });

    }
    private void saveAccountSetupInformation() {
        String userName = UserName.getText().toString();
        String fullName = FullName.getText().toString();
        String countyName = County.getText().toString();

        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Please provide your username", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(fullName)){
            Toast.makeText(this, "Please provide your full name", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(countyName)){
            Toast.makeText(this, "Please provide your county name", Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.setTitle("Saving Information");
            progressDialog.setMessage("Please Wait, while we are creating your new account");
            progressDialog.show();

            HashMap userMap = new HashMap();
            userMap.put("userName",userName);
            userMap.put("fullName",fullName);
            userMap.put("countyName",countyName);
            userRef.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){
                        sendUserToHomeActivity();
                        Toast.makeText(SetupActivity.this, "Your Account was created successfully", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                    else{
                        String message = task.getException().getMessage();
                        Toast.makeText(SetupActivity.this, "Error occurred:"+ message, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });

        }

    }

    private void sendUserToHomeActivity() {
        startActivity(new Intent(SetupActivity.this,HomeActivity.class));
        finish();
    }
}
