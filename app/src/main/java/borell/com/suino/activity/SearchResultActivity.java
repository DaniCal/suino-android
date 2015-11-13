package borell.com.suino.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import borell.com.suino.R;
import borell.com.suino.adapter.SearchResultAdapter;
import borell.com.suino.model.SuinoResultItem;


public class SearchResultActivity  extends AppCompatActivity {

    RecyclerView rv_searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_result);
        initToolbar();

        rv_searchResults = (RecyclerView) findViewById(R.id.rv_search_results);

        ArrayList<SuinoResultItem> results = new ArrayList<>();
        results.add(new SuinoResultItem());
        results.add(new SuinoResultItem());
        results.add(new SuinoResultItem());
        results.add(new SuinoResultItem());
        results.add(new SuinoResultItem());
        results.add(new SuinoResultItem());
        results.add(new SuinoResultItem());
        results.add(new SuinoResultItem());
        results.add(new SuinoResultItem());
        results.add(new SuinoResultItem());

        SearchResultAdapter adapter = new SearchResultAdapter(this, results);
        rv_searchResults.setLayoutManager(new LinearLayoutManager(this));
        rv_searchResults.setAdapter(adapter);

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
}
