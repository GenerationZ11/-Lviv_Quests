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
import android.util.Log;
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
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
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
import java.util.Arrays;


public class test_FB extends AppCompatActivity {
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    ImageView imageView;
    Button share;
    ShareButton fbShareButton;
    TextView email, gender, facebookName,birth;
    ProfilePictureView profilePictureView;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_test__fb);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        share=(Button) findViewById(R.id.share) ;
        gender = (TextView) findViewById(R.id.txtGender);
        facebookName = (TextView) findViewById(R.id.txtName);
        birth = (TextView) findViewById(R.id.txtBd);
        profilePictureView = (ProfilePictureView) findViewById(R.id.photo1);
        email = (TextView) findViewById(R.id.email);
        fbShareButton = (ShareButton) findViewById(R.id.fb_share_button);
       loginButton = (LoginButton) findViewById(R.id.login_button);
        share();
        loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));
        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("Main", response.toString());
                                setProfileToView(object);
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(test_FB.this, "error to Login Facebook", Toast.LENGTH_SHORT).show();
            }
        });







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







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    void share(){
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_STREAM,Uri.parse("data:image/jpeg;base64,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") );
                startActivity(Intent.createChooser(i, "Share Image"));
            }
        });
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setQuote("Generation Z ")
                .setContentUrl(Uri.parse("https://en.wikipedia.org/wiki/Internet_of_things"))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#ConnectTheWorld")
                        .build())
                .build();

        fbShareButton.setShareContent(content);

}

    private void setProfileToView(JSONObject jsonObject) {
        try {
            email.setText(jsonObject.getString("email"));
            gender.setText(jsonObject.getString("gender"));
            facebookName.setText(jsonObject.getString("name"));
            birth.setText(jsonObject.getString("birthday"));
            profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
            profilePictureView.setProfileId(jsonObject.getString("id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


















