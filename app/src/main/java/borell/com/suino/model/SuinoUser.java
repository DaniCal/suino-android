package borell.com.suino.model;

import android.net.Uri;

import com.facebook.Profile;


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
}
