package l.generationz.first_program.m_UI;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import l.generationz.first_program.FeedDetails;
import l.generationz.first_program.R;

public class FeedAdapter extends ArrayAdapter<FeedDetails> {

    private final Activity context;
    private final List<FeedDetails> list;

    private ImageView imageView;

    public FeedAdapter(Activity context, List<FeedDetails> list) {
        super(context, R.layout.item_layout, list);
        this.context = context;
        this.list = list;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_layout, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.feedDescription);
        imageView = (ImageView) rowView.findViewById(R.id.feedImage);
        txtTitle.setText(list.get(position).getName());
        imageView.setImageBitmap(list.get(position).getBinaryImage());
        //loadImageByName(list.get(position).getImage());
        /*extratxt.setText("Description "+itemname[position]);*/
        return rowView;
    }
}