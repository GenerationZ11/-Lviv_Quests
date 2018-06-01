package l.generationz.first_program;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

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
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sviatik on 25.12.2017.
 */

public class LvivQuests {
    private static LvivQuests instance;
    private HashMap<String,QuestDetails> quests = new HashMap<String, QuestDetails>();
    private HashMap<String, Bitmap> images = new HashMap<String, Bitmap>();
    private List<String> finished_quests = new ArrayList<String>();
    private List<String> likes_feed = new ArrayList<String>();
    private String user;

    private LvivQuests() {
        initQuests();
        initMyLikes();
    }

    public static LvivQuests getInstance() {
        if (instance == null) {
            instance = new LvivQuests();
        }
        return instance;
    }

    public static void setInstance(LvivQuests instance) {
        LvivQuests.instance = instance;
    }

    public HashMap<String, QuestDetails> getQuests() {
        return quests;
    }

    public void setQuests(HashMap<String, QuestDetails> quests) {
        this.quests = quests;
    }

    public HashMap<String, Bitmap> getImages() {
        return images;
    }

    public void setImages(HashMap<String, Bitmap> images) {
        this.images = images;
    }

    public List<String> getFinished_quests() {
        return finished_quests;
    }

    public void setFinished_quests(List<String> finished_quests) {
        this.finished_quests = finished_quests;
    }

    public List<String> getfinished_quests() {
        return finished_quests;
    }

    public List<String> getLikes_feed() {
        return likes_feed;
    }

    public void setLikes_feed(List<String> likes_feed) {
        this.likes_feed = likes_feed;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private void initQuests() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Quests/");
        Query phoneQuery = myRef.orderByKey();
        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    QuestDetails quest = singleSnapshot.getValue(QuestDetails.class);
                    quests.put(singleSnapshot.getKey(), quest);
                    loadImageByName(quest.getImage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initMyLikes() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Likes/");
        Query phoneQuery = myRef.orderByKey();
        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    Like like = singleSnapshot.getValue(Like.class);
                    if (like.getUser().equalsIgnoreCase(user)) {
                        likes_feed.add(like.getFeed());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadImageByName(final String name) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageRef = firebaseStorage.getReference();
        StorageReference pathReference = storageRef.child("Quests/" + name);

        final long ONE_MEGABYTE = 1024 * 1024;
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                if (bytes.length > 0) {
                    Bitmap myBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    images.put(name, myBitmap);
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
