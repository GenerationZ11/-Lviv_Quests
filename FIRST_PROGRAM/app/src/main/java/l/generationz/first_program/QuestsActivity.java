package l.generationz.first_program;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.UUID;


public class QuestsActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String currentQuest = null;
    private TextView questNameView;
    private TextView questDescriptionView;

    private TextView questTaskView;
    private ImageView questImage;
    private ImageButton btnCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);

        questNameView = findViewById(R.id.quest_name);
        questDescriptionView = findViewById(R.id.quest_description);
        questTaskView = findViewById(R.id.quest_task);
        questImage = findViewById(R.id.quest_image );
        btnCamera = findViewById(R.id.btnCamera);
        ImageButton map=(ImageButton) findViewById(R.id.map2);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuestsActivity.this,Map.class));
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            currentQuest = bundle.getString("questname");

        }
        if (currentQuest != null) {
            initLoadFromDbBtn();
            btnCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenCamera();
                }
            });
        }


    }

    private void initLoadFromDbBtn() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Quests/");
        Query phoneQuery = myRef.orderByKey();
        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    if (singleSnapshot.getKey().equalsIgnoreCase(currentQuest)) {
                        QuestDetails quest = singleSnapshot.getValue(QuestDetails.class);
                        questNameView.setText(quest.getName());
                        questDescriptionView.setText(quest.getDescription());
                        questTaskView.setText(quest.getTask() );
                        loadImageByName(quest.getImage());
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void OpenCamera() {
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            final String id = UUID.randomUUID().toString();

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            StorageReference storageRef = firebaseStorage.getReference();
            StorageReference mountainsRef = storageRef.child(id + ".jpg");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            boolean compress = imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();

            UploadTask uploadTask = mountainsRef.putBytes(bytes);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //Handle Success

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    myRef.child("Feed").child(id).child("name").setValue(questNameView.getText().toString());
                    myRef.child("Feed").child(id).child("image").setValue(id + ".jpg");
                    myRef.child("Feed").child(id).child("likes").setValue(0);
                    myRef.child("Feed").child(id).child("date").setValue(new Date().getTime());

                }
            });
        }
    }
    private void loadImageByName(String name) {


        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageRef = firebaseStorage.getReference();
        StorageReference pathReference = storageRef.child("Quests/"+name);

        final long ONE_MEGABYTE = 1024 * 1024;
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                if (bytes.length > 0) {
                    Bitmap myBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    questImage.setImageBitmap(myBitmap);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
}
