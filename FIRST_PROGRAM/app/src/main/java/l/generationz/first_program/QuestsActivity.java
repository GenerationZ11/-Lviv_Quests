package l.generationz.first_program;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class QuestsActivity extends AppCompatActivity {

    private String currentQuest = null;
    private TextView questNameView;
    private TextView questDescriptionView;
    private TextView questTaskView;
    private ImageView questImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);

        questNameView = findViewById(R.id.quest_name);
        questDescriptionView = findViewById(R.id.quest_description);
        questTaskView = findViewById(R.id.quest_task);
        questImage = findViewById(R.id.quest_image );

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            currentQuest = bundle.getString("questname");

        }
        if (currentQuest != null) {
            initLoadFromDbBtn();
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
