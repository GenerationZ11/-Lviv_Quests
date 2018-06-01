package l.generationz.first_program;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Tab1 extends android.support.v4.app.Fragment {
  
  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.tab1, container, false);

      Button button = (Button) rootView.findViewById(R.id.quests1);
    Button chocolate = (Button) rootView.findViewById(R.id.chocolate);
    Button ghost = (Button) rootView.findViewById(R.id.ghost);
    Button massons = (Button) rootView.findViewById(R.id.masson);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (getActivity() != null) { // check if activity not null
          Intent intent = new Intent(getActivity(), QuestsActivity.class);
          Bundle bundle = new Bundle();
          bundle.putString("questname", "ПідкориРатушу");
          intent.putExtras(bundle);
          getActivity().startActivity(intent);
        }
      }
    });
    chocolate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (getActivity() != null) { // check if activity not null
          Intent intent = new Intent(getActivity(), QuestsActivity.class);
          Bundle bundle = new Bundle();
          bundle.putString("questname", "Фабрика Шоколаду");
          intent.putExtras(bundle);
          getActivity().startActivity(intent);
        }
      }
    });
    ghost.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (getActivity() != null) { // check if activity not null
          Intent intent = new Intent(getActivity(), QuestsActivity.class);
          Bundle bundle = new Bundle();
          bundle.putString("questname", "Знайди примару");
          intent.putExtras(bundle);
          getActivity().startActivity(intent);
        }
      }
    });
    massons.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (getActivity() != null) { // check if activity not null
          Intent intent = new Intent(getActivity(), QuestsActivity.class);
          Bundle bundle = new Bundle();
          bundle.putString("questname", "Масони");
          intent.putExtras(bundle);
          getActivity().startActivity(intent);
        }
      }
    });
        return rootView;
    }

}

