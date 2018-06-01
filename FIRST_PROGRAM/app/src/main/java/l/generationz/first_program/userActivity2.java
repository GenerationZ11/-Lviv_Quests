package l.generationz.first_program;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class userActivity2 extends AppCompatActivity {
    private CallbackManager callbackManager;
    private TextView facebookName;
    private LoginButton loginButton;
    private ProfilePictureView profilePictureView;
    TextView kubok,coin,text;
    final String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_user2);
        text =  findViewById(R.id.text);
        kubok = findViewById(R.id.textView2);
        coin = findViewById(R.id.textView3);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login) ;
        facebookName = (TextView) findViewById(R.id.name);
        profilePictureView = (ProfilePictureView) findViewById(R.id.photo1);
        loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));
        callbackManager = CallbackManager.Factory.create();
        JSONObject postData = new JSONObject();


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(final LoginResult loginResult) {
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
                Toast.makeText(userActivity2.this, "error to Login Facebook", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void setProfileToView(JSONObject jsonObject) {
        try {

            facebookName.setText(jsonObject.getString("name"));
            profilePictureView.setPresetSize(ProfilePictureView.LARGE);
            profilePictureView.setProfileId(jsonObject.getString("id"));
            kubok.setText("1");
            coin.setText("10");


           text.setVisibility(View.GONE);
           findViewById(R.id.imageView18).setVisibility(View.VISIBLE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
