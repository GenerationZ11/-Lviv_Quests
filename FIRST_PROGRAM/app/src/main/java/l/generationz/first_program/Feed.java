package l.generationz.first_program;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import l.generationz.first_program.m_UI.FeedAdapter;

public class Feed extends AppCompatActivity {
    ListView listView;
    FloatingActionButton goupload, refresh;

    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    FeedAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        final List<FeedDetails> feedDetailsList = new ArrayList<FeedDetails>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Feed/");
        Query phoneQuery = myRef.orderByKey();
        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    FeedDetails feedDetails = singleSnapshot.getValue(FeedDetails.class);
                    feedDetailsList.add(feedDetails);
                    loadImageByName(feedDetails);
                }
                initList(feedDetailsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initList(final List<FeedDetails> list) {
        FeedAdapter adapter = new FeedAdapter(this, list);
        listView = (ListView) findViewById(R.id.feedList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                FeedDetails Slecteditem = list.get(+position);
                Toast.makeText(getApplicationContext(), Slecteditem.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadImageByName(final FeedDetails details) {

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageRef = firebaseStorage.getReference();
        StorageReference pathReference = storageRef.child(details.getImage());

        final long ONE_MEGABYTE = 1024 * 1024;
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                if (bytes.length > 0) {
                    Bitmap myBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    details.setBinaryImage(myBitmap);
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
