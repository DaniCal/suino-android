package borell.com.suino.activity;

import com.google.android.gms.maps.model.LatLng;

import borell.com.suino.model.SuinoCourse;

/**
 * Created by daniellohse on 9/29/15.
 */
public interface CreateCourseInterface {
    public void onShowMap();
    void onSaveCourse(SuinoCourse course);
    void onConfirmLocation(LatLng latLng);

}
