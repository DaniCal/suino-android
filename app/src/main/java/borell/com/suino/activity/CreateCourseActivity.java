package borell.com.suino.activity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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
    private CardView createCourse;
    private Activity activity;
    Fragment createCourseFragment;
    SuinoCourse course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_course);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Course");
        createCourseFragment = new CreateCourseFragment();
        displayView();
        activity = this;
        createCourse = (CardView) findViewById(R.id.cv_createCourse);
        createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    public void onShowMap(){
        SupportMapFragment mMapFragment = new MyMapFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_create_course, mMapFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveCourse(SuinoCourse course) {
        this.course = course;
    }

    private void displayView() {

        if (createCourseFragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_create_course, createCourseFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container_create_course);
        if (f instanceof MyMapFragment){
            displayView();
        }else if(f instanceof CreateCourseFragment){
            Intent intent = new Intent(this, Suino.class);
            startActivity(intent);
        }
    }
}
