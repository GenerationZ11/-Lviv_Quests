package l.generationz.first_program.m_UI;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import l.generationz.first_program.R;

public class FeedAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> list;

    public FeedAdapter(Activity context, List<String> list) {
        super(context, R.layout.item_layout, list);
        this.context = context;
        this.list = list;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_layout, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.feedDescription);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.feedImage);

        txtTitle.setText(list.get(position));
        /*imageView.setImageResource(imgid[position]);
        extratxt.setText("Description "+itemname[position]);*/
        return rowView;
    }
}