package borell.com.suino.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import borell.com.suino.Http.HttpCallback;
import borell.com.suino.Http.HttpManager;
import borell.com.suino.R;
import borell.com.suino.activity.UIInterface;
import borell.com.suino.model.SuinoUser;


public class LoginFragment extends Fragment {

    LoginButton loginButton;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;
    ProfileTracker profileTracker;
    Activity activity;
    SuinoUser user;
    HttpManager httpUtils;
    UIInterface mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        activity = getActivity();
        httpUtils = new HttpManager();
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (UIInterface) activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getView().setVisibility(View.GONE);
        loginButton = (LoginButton) getView().findViewById(R.id.login_button);


        loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_friends, user_likes"));
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        callbackManager = CallbackManager.Factory.create();


        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                Log.d("FacebookLogin", "Access Token");

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                if(currentProfile != null && currentProfile.getId() != null){
                    user = new SuinoUser(currentProfile);
                }
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                Toast.makeText(getActivity(), "Login Succeed", Toast.LENGTH_SHORT).show();
                Log.d("FacebookLogin", "Succeed");

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code

                                try {
                                    String email = object.getString("email");
                                    String gender = object.getString("gender");
                                    if(user != null){
                                        user.setFbEmail(email);
                                        user.setFbGender(gender);
                                        login();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email, gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Login Canceled", Toast.LENGTH_SHORT).show();
                Log.d("FacebookLogin", "Cancel");

            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getActivity(), "Login Error", Toast.LENGTH_SHORT).show();
                Log.d("FacebookLogin", "Error");

            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    public void show(){
        getView().setVisibility(View.VISIBLE);
    }

    public void hide(){
        getView().setVisibility(View.GONE);
    }


    private void login(){

        httpUtils.getRequest(user.createLoginUrl(), new HttpCallback() {
            @Override
            public void onSuccess(Response response) {
                if(response.code() == 204){
                    register();
                }else if(response.code() == 200){
                    mCallback.onHideLogin();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(activity, "Login Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void register(){
        httpUtils.postRequest(user.createRegisterUrl(),user.createRegisterJson(),  new HttpCallback() {
            @Override
            public void onSuccess(Response response) {
                if(response.code() == 201){
                    mCallback.onHideLogin();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(activity, "Register Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
