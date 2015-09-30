package borell.com.suino.activity;

import android.app.Activity;
import android.location.Location;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;


import borell.com.suino.R;
import borell.com.suino.fragment.CreateCourseFragment;
import borell.com.suino.fragment.MyMapFragment;
import borell.com.suino.model.SuinoCourse;


public class CreateCourseActivity extends AppCompatActivity implements CreateCourseInterface {
    private Toolbar mToolbar;
    private Button createCourse;
    private Activity activity;
    private SuinoCourse course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_course);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Course");
        displayView();

        activity = this;
        createCourse = (Button) findViewById(R.id.button_create_course);
        createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "TODO: Create Course", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);}

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void showMap(){
        SupportMapFragment mMapFragment = new MyMapFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_create_course, mMapFragment);
        fragmentTransaction.commit();
    }

    private void displayView() {
        Fragment fragment = new CreateCourseFragment();

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_create_course, fragment);
            fragmentTransaction.commit();
        }
    }
}
