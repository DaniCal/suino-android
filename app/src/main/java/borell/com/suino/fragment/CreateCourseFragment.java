package borell.com.suino.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import borell.com.suino.R;
import borell.com.suino.activity.CreateCourseInterface;
import borell.com.suino.model.SuinoCourse;


public class CreateCourseFragment extends Fragment {

    private CreateCourseInterface mCallback;
    private Activity activity;
    private CardView cv_category;
    private TextView tv_category;
    private TextView tv_price;
    private SeekBar sb_price;
    private CardView cv_location;
    private SuinoCourse course;

    private final int PRICE_MAX = 50;
    private final int PRICE_DEFAULT =15;

    public CreateCourseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_course, container, false);
    }
    @Override
    public void onAttach(final Activity activity){
        mCallback = (CreateCourseInterface) activity;
        super.onAttach(activity);
        this.activity = activity;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCardViews();
        course = new SuinoCourse("123123","Dani","somedomain.com/pic");
    }

    private void initCardViews(){
        initCategoryCardView();
        initTagCardView();
        initPriceCardView();
        initGroupSizeCardView();
        initLevelCardView();
        initDescriptionCardView();
        initLocationCardView();
        initCourseDateCardView();
    }


    private void initCategoryCardView(){
        cv_category = (CardView) getView().findViewById(R.id.cv_category);
        tv_category = (TextView) getView().findViewById(R.id.tv_category);
        cv_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDialog dialog = new MaterialDialog.Builder(activity)
                        .title("Choose Category")
                        .items(R.array.category_list)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                course.setDescription(text.toString());
                                tv_category.setText(text);
                                tv_category.setTextColor(getResources().getColor(R.color.textColorPrimaryDark));
                                dialog.hide();
                                return true;
                            }
                        })
                        .show();
            }
        });
    }

    private void initTagCardView(){

    }

    private void initPriceCardView(){
        tv_price = (TextView) getView().findViewById(R.id.tv_price);
        sb_price = (SeekBar) getView().findViewById(R.id.sb_price);
        sb_price.setMax(PRICE_MAX);
        sb_price.setProgress(PRICE_DEFAULT);
        course.setPrice(PRICE_DEFAULT);
        sb_price.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_price.setText(seekBar.getProgress() + "$ per hour");
                course.setPrice(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initGroupSizeCardView(){

    }

    private void initLevelCardView(){

    }

    private void initDescriptionCardView(){

    }

    private void initLocationCardView(){
        cv_location =(CardView) getView().findViewById(R.id.cv_location);
        cv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onShowMap();
            }
        });
    }

    private void initCourseDateCardView(){

    }
}



