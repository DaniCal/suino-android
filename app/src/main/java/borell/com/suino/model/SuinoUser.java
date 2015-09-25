package borell.com.suino.model;

import android.net.Uri;
import android.util.Log;

import com.facebook.Profile;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class SuinoUser {
    private String fbFirstName;
    private String fbLastName;
    private String fbId;
    private Uri fbLink;
    private Uri fbPictureLink;
    private String fbEmail;
    private String fbGender;

    public SuinoUser(Profile profile){
        this.fbFirstName = profile.getFirstName();
        this.fbLastName = profile.getLastName();
        this.fbId = profile.getId();
        this.fbLink = profile.getLinkUri();
        this.fbPictureLink = profile.getProfilePictureUri(180,180);
    }

    public void setFbEmail(String email){
        this.fbEmail = email;
    }

    public void setFbGender(String gender){
        this.fbGender = gender;
    }


    public String createLoginUrl(){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("192.168.1.65")
                .port(3000)
                .addPathSegment("login")
                .addQueryParameter("fbName", this.getFbFirstName())
                .addQueryParameter("fbId", this.getFbId())
                .addQueryParameter("deviceToken", "123123")
                .addQueryParameter("platform", "android")
                .build();
        return url.toString();
    }

    public String createRegisterUrl(){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("192.168.1.65")
                .port(3000)
                .addPathSegment("register")
                .build();
        return url.toString();
    }

//    public void login(){
//        try {
//            get(createLoginUrl());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }




    public String getFbFirstName() {
        return fbFirstName;
    }

    public String getFbLastName() {
        return fbLastName;
    }

    public String getFbId() {
        return fbId;
    }

    public Uri getFbLink() {
        return fbLink;
    }

    public Uri getFbPictureLink() {
        return fbPictureLink;
    }

    public String getFbEmail() {
        return fbEmail;
    }

    public String getFbGender() {
        return fbGender;
    }
}
