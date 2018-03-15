package l.generationz.first_program;
;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Path;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.share.Sharer;

import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;


public class test_FB extends AppCompatActivity {
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    ImageView imageView;
    TextView txtName, txtURL, txtGender,txtBd;
    Button btnShare,insta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_test__fb);


       insta=(Button) findViewById(R.id.btnShareInst) ;
       insta.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(Intent.ACTION_SEND);
               i.setType("image/jpeg");
               i.putExtra(Intent.EXTRA_STREAM, Uri.parse());//savedPhotoPath is the path of my picture stored somewhere in the sdcard
               startActivity(Intent.createChooser(i, "Share Image"));
           }
       });




        Button share = (Button) findViewById(R.id.post);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

      /*  share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* SharePhoto photo = new SharePhoto.Builder()
                       .setImageUrl(Uri.parse("http://iot.lviv.ua/wp-content/uploads/2015/05/imgpsh.png"))
                        .build();

                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();

                shareDialog.show(content);

                ShareLinkContent content = new ShareLinkContent.Builder().build();
                shareDialog.show(content);

                }
        });
*/
        shareDialog = new ShareDialog(this);

        imageView = (ImageView) findViewById(R.id.imgPhoto);
        txtName = (TextView) findViewById(R.id.txtName);
        txtURL = (TextView) findViewById(R.id.txtURL);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtBd = (TextView) findViewById(R.id.txtBd);







        //Share Dialog
        //You cannot preset the shared link in design time, if you do so, the fb share button will
        //look disabled. You need to set in the code as below
        ShareButton fbShareButton = (ShareButton) findViewById(R.id.fb_share_button);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setQuote("Generation Z ")
                .setContentUrl(Uri.parse("https://en.wikipedia.org/wiki/Internet_of_things"))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#ConnectTheWorld")
                        .build())
                .build();

        fbShareButton.setShareContent(content);
    }


}


















