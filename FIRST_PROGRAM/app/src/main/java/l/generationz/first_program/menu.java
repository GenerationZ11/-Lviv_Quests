package l.generationz.first_program;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import android.widget.Button;
import android.widget.ImageButton;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button quests=(Button) findViewById(R.id.questsBtn);
        Button map=(Button) findViewById(R.id.map2Btn);
        Button feed = (Button) findViewById(R.id.feedBtn);
        Button userRoom = (Button) findViewById(R.id.userBtn);


        userRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this,userActivity2.class));
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this,Map.class));
            }
        });
        quests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this,questList.class));
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this , Feed.class));
            }
        });


    }
}
