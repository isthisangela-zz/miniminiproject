package com.projects.miniminiproject.miniminiproject;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Creates a new social given the image and other information
 */
public class NewSocialActivity extends AppCompatActivity {

    private Uri file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_social);
        Button submitButton = (Button)findViewById(R.id.submitButton);
        Button button2 = (Button)findViewById(R.id.button2);
        final EditText nameEditText = (EditText)findViewById(R.id.nameEditText);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        //TODO this looks messy tho
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                final String key = ref.child("socials").push().getKey();
                StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://miniminiproject-c244d.appspot.com");
                StorageReference riversRef = storageRef.child(key + ".png");
                riversRef.putFile(file).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(NewSocialActivity.this, "need an image!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String caption = nameEditText.getText().toString();
                        String creator = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                        ref.child("socials").child(key).child("name").setValue(caption);
                        ref.child("socials").child(key).child("creator").setValue(creator);
                        startActivity(intent);
                    }
                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            file = data.getData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
