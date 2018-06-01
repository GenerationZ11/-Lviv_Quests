package l.generationz.first_program;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Tab2 extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);
        Button freedom = (Button) rootView.findViewById(R.id.freedom);
        Button imposs = (Button) rootView.findViewById(R.id.impossible);
        Button call = (Button) rootView.findViewById(R.id.call);
        Button nine = (Button) rootView.findViewById(R.id.nine);

        freedom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) { // check if activity not null
                    Intent intent = new Intent(getActivity(), QuestsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("questname", "Statuya Svobody");
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }
            }
        });
        imposs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) { // check if activity not null
                    Intent intent = new Intent(getActivity(), QuestsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("questname", "Pid zolotou rozou");
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) { // check if activity not null
                    Intent intent = new Intent(getActivity(), QuestsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("questname", "Vysokyi Zamok");
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) { // check if activity not null
                    Intent intent = new Intent(getActivity(), QuestsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("questname", "Visit SoftServe");
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }
            }
        });
        return rootView;
    }
}

