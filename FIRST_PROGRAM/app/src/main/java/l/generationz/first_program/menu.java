package l.generationz.first_program;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import android.widget.ImageButton;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ImageButton qusets=(ImageButton) findViewById(R.id.FB);
        ImageButton map=(ImageButton) findViewById(R.id.map2);
        ImageButton FB = (ImageButton) findViewById(R.id.FB);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this,Map.class));
            }
        });
        qusets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent(menu.this, QuestsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("questname", "Pid zolotou rozou");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        FB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this , LogInFB.class));
            }
        });

    }
}
