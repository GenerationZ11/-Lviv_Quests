package l.generationz.first_program;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuQuestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_quests);

        Button quests = (Button) findViewById(R.id.quests1);
        ImageButton quests2 = (ImageButton) findViewById(R.id.quests2);
        ImageButton quests3 = (ImageButton) findViewById(R.id.quests3);
        ImageButton quests4 = (ImageButton) findViewById(R.id.quests4);

        quests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MenuQuestsActivity.this, QuestsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("questname", "Vysokyi Zamok");
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
        quests2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MenuQuestsActivity.this, QuestsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("questname", "Pid zolotou rozou");
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });quests3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MenuQuestsActivity.this, QuestsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("questname", "Statuya Svobody");
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });quests4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MenuQuestsActivity.this, QuestsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("questname", "Visit SoftServe");
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }
}
