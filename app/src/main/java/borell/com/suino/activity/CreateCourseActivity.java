package borell.com.suino.activity;

import android.content.Intent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.okhttp.Response;

import borell.com.suino.Http.HttpCallback;
import borell.com.suino.Http.HttpManager;
import borell.com.suino.R;
import borell.com.suino.fragment.CreateCourseFragment;
import borell.com.suino.fragment.SelectLocationFragment;
import borell.com.suino.model.SuinoCourse;


public class CreateCourseActivity extends AppCompatActivity implements CreateCourseInterface {
    private CardView createCourse;
    CreateCourseFragment createCourseFragment;
    SuinoCourse course;
    private HttpManager httpUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_course);
        httpUtils = new HttpManager();

        initToolbar();
        initCreateCourseFragment();
        initCreateCourseButton();

        displayView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Course");
    }

    private void initCreateCourseFragment(){
        if(createCourseFragment == null){
            createCourseFragment = new CreateCourseFragment();
        }
    }

    private void initCreateCourseButton(){
        createCourse = (CardView) findViewById(R.id.cv_createCourse);
        createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createCourseFragment.getCourse().isValid()) {
                    createCourseRequest(createCourseFragment.getCourse());
                } else {
                    Toast.makeText(CreateCourseActivity.this, createCourseFragment.getCourse().getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
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
        SelectLocationFragment mMapFragment = new SelectLocationFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_create_course, mMapFragment);
        fragmentTransaction.commit();
        hideCreateCourseButton();
    }

    @Override
    public void onSaveCourse(SuinoCourse course) {
        this.course = course;
    }

    @Override
    public void onConfirmLocation(LatLng latLng) {
        if(latLng != null){
            createCourseFragment.setLocation(latLng);
            displayView();
        }
    }

    @Override
    public void onDeleteTag(int position) {
        createCourseFragment.deleteTag(position);
    }

    private void displayView() {

        if(createCourseFragment == null){
            initCreateCourseFragment();
        }

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_create_course, createCourseFragment);
            fragmentTransaction.commit();

            showCreateCourseButton();

    }

    private void showCreateCourseButton(){
        if(createCourse != null){
            createCourse.setVisibility(View.VISIBLE);
        }
    }

    private void hideCreateCourseButton(){
        if(createCourse != null){
            createCourse.setVisibility(View.GONE);
        }
    }

    private void createCourseRequest(SuinoCourse course){
        final MaterialDialog createCourseDialog = new MaterialDialog.Builder(this)
                .title("Create course")
                .content("Loading")
                .progress(true, 0)
                .build();

        createCourseDialog.show();


        httpUtils.postRequest(course.createCreateCourseUrl(),course.createPostCourseJson(),  new HttpCallback() {
            @Override
            public void onSuccess(Response response) {
                if (response.code() == 201) {
                    Toast.makeText(CreateCourseActivity.this, "Create Course Success" + response.message(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(CreateCourseActivity.this, "Create Course Error" + response.message(), Toast.LENGTH_SHORT).show();
                }

                createCourseDialog.dismiss();
            }


            @Override
            public void onError() {
                Toast.makeText(CreateCourseActivity.this, "Create Course Error", Toast.LENGTH_SHORT).show();
                createCourseDialog.dismiss();

            }

            @Override
            public void onSuccessLoadingImage(Bitmap result) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container_create_course);
        if (f instanceof SelectLocationFragment){
            displayView();

        }else if(f instanceof CreateCourseFragment){
            Intent intent = new Intent(this, Suino.class);
            startActivity(intent);
        }
    }
}
