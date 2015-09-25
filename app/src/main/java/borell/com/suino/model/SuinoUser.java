package borell.com.suino.model;

import android.net.Uri;
import android.util.Log;

import com.facebook.Profile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;

import borell.com.suino.activity.Suino;


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

    public String createRegisterJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SuinoUser.class, new SuinoUserSerializer());
//        gsonBuilder.registerTypeAdapter(ITTInvited.class, new InvitedDeserializer());
        final Gson gson = gsonBuilder.create();
        return gson.toJsonTree(this, SuinoUser.class).toString();
    }

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


    private class SuinoUserSerializer implements JsonSerializer<SuinoUser> {

        @Override
        public JsonElement serialize(SuinoUser src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonObject jsonObject = new JsonObject();


            jsonObject.addProperty("fbId", getFbId());
            jsonObject.addProperty("fbName", getFbFirstName());
            jsonObject.addProperty("email", getFbEmail());
            jsonObject.addProperty("platform", "android");
            jsonObject.addProperty("deviceToken", "123123");

            return jsonObject;
        }
    }

//    private class SuinoUserDeserializer implements JsonDeserializer<SuinoUser> {
//        public SuinoUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
//                throws JsonParseException {
//
//            final JsonObject jsonObject = json.getAsJsonObject();
//
//            final String fbId = jsonObject.get("fbId").getAsString();
//            final String fbName = jsonObject.get("fbName").getAsString();
//
//
//            return new SuinoUser(fb_id, fb_name, readString);
//
//        }
//    }
}
