package borell.com.suino.model;

import android.net.Uri;

import com.facebook.Profile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.squareup.okhttp.HttpUrl;

import java.lang.reflect.Type;

import borell.com.suino.Http.HttpConfig;
import borell.com.suino.Http.HttpValues;


public class SuinoUser {
    private String fbFirstName;
    private String fbLastName;
    private String fbId;
    private Uri fbLink;
    private Uri fbPictureLink;
    private String fbEmail;
    private String fbGender;

    private static final String VALUE_FB_NAME = "fbName";
    private static final String VALUE_FB_ID = "fbId";
    private static final String VALUE_DEVICE_TOKEN = "deviceToken";
    private static final String VALUE_PLATFORM = "platform";
    private static final String VALUE_EMAIL = "email";


    public SuinoUser(Profile profile){
        this.fbFirstName = profile.getFirstName();
        this.fbLastName = profile.getLastName();
        this.fbId = profile.getId();
        this.fbLink = profile.getLinkUri();
        this.fbPictureLink = profile.getProfilePictureUri(180,180);
    }

    public SuinoUser(String firstName, String lastName, String fbId, Uri fbPictureLink){
        this.fbFirstName = firstName;
        this.fbLastName = lastName;
        this.fbId = fbId;
        this.fbPictureLink = fbPictureLink;
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
                .host(HttpConfig.HOST)
                .port(HttpConfig.PORT)
                .addPathSegment(HttpValues.PATH_LOGIN)
                .addQueryParameter(VALUE_FB_NAME, this.getFbFirstName())
                .addQueryParameter(VALUE_FB_ID, this.getFbId())
                .addQueryParameter(VALUE_DEVICE_TOKEN, "123123")
                .addQueryParameter(VALUE_PLATFORM, "android")
                .build();
        return url.toString();
    }

    public String createRegisterUrl(){
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(HttpConfig.HOST)
                .port(HttpConfig.PORT)
                .addPathSegment(HttpValues.PATH_REGISTER)
                .build();
        return url.toString();
    }

    public String createRegisterJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SuinoUser.class, new SuinoUserSerializer());
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


            jsonObject.addProperty(VALUE_FB_ID, getFbId());
            jsonObject.addProperty(VALUE_FB_NAME, getFbFirstName());
            jsonObject.addProperty(VALUE_EMAIL, getFbEmail());
            jsonObject.addProperty(VALUE_PLATFORM, "android");
            jsonObject.addProperty(VALUE_DEVICE_TOKEN, "123123");

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
