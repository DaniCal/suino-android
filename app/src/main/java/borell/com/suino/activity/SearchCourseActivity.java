package borell.com.suino.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import borell.com.suino.R;
import borell.com.suino.fragment.SearchButtonFragment;
import borell.com.suino.fragment.SearchFilterFragment;
import borell.com.suino.view.LatoEditText;

public class SearchCourseActivity extends AppCompatActivity implements SearchCourseInterface{

    CardView searchCourse;
    LatoEditText et_keywords;
    SearchButtonFragment searchButtonFragment;
    SearchFilterFragment searchFilterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        initToolbar();
        showSearchButtonFragment();
        initSearchCourseButton();
        initEditTextView();
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
        getSupportActionBar().setTitle("Search Course");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Suino.class);
        startActivity(intent);
    }

    private void initSearchCourseButton(){
        searchCourse = (CardView) findViewById(R.id.cv_search_course_button);
        searchCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCourseRequest();
            }
        });
    }

    private void initEditTextView(){
        et_keywords = (LatoEditText) findViewById(R.id.et_search_keywords);
    }

    private void searchCourseRequest(){
//        searchFilterFragment.getFilterOption().createSearchUrl();
        Intent intent = new Intent(this, SearchResultActivity.class);
        startActivity(intent);

    }

    public void showSearchButtonFragment(){
        searchButtonFragment = new SearchButtonFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_search, searchButtonFragment);
        fragmentTransaction.commit();
    }

    public void showSearchFilterFragment(){
        searchFilterFragment = new SearchFilterFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_search, searchFilterFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void showFilter() {
        showSearchFilterFragment();
    }
}
