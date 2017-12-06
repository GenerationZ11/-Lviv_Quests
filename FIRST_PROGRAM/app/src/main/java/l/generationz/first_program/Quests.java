package l.generationz.first_program;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Quests extends AppCompatActivity {

    TextView nameView;
    TextView descriptionView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);
        ImageButton map=(ImageButton) findViewById(R.id.map2);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Quests. this, Map.class));
            }
        });
        nameView = findViewById(R.id.infa_about_quest);
        descriptionView = findViewById(R.id.descr);


        InputStream is = this.getResources().openRawResource(R.raw.desc);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        if (is != null) {
            String data = "";
            String json= "";
            try {
                while ((data = reader.readLine()) != null) {
                    json+=data;
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                JSONArray array = new JSONArray(json);
                JSONObject object = array.getJSONObject(0);
                String name = object.getString("name");
                String descript = object.getString("description");
                nameView.setText(name);
                descriptionView.setText(descript);
            }catch(Exception e){

            }
        }

    }
}
