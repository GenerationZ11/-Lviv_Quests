package l.generationz.first_program;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Feed/");
        Query phoneQuery = myRef.orderByKey();
        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    QuestDetails quest = singleSnapshot.getValue(QuestDetails.class);
                    quests.add(quest);
                    loadImageByName(quest.getImage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final List<String> test = new ArrayList<String>() {{
            add("A");
            add("B");
        }};
        FeedAdapter adapter = new FeedAdapter(this, test);
        listView = (ListView) findViewById(R.id.feedList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = test.get(+position);
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
