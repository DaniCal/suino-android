package borell.com.suino.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.model.LatLng;

import java.util.LinkedHashMap;

import borell.com.suino.R;
import borell.com.suino.activity.CreateCourseInterface;
import borell.com.suino.model.SuinoCourse;


public class CreateCourseFragment extends Fragment {

    private CreateCourseInterface mCallback;
    private Activity activity;
    private ScrollView scrollView;
    private Toolbar toolbar;
    private CardView cv_category;
    private TextView tv_category;
    private TextView tv_price;
    private SeekBar sb_price;
    private CardView cv_location;
    private CardView cv_increase;
    private CardView cv_decrease;
    private CardView cv_description;
    private TextView tv_groupSize;
    private EditText et_description;
    private SuinoCourse course;
    private LinearLayout ll_default_map;
    private RelativeLayout iv_map_circle;

    private final int PRICE_MAX = 50;
    private final int PRICE_DEFAULT = 15;
    private final int GROUP_SIZE_MAX = 20;
    private final int GROUP_SIZE_DEFAULT= 2;
    private final int DESCRIPTION_MAX = 200;

    MapFormFragment mapFragment;
    LatLng latLng;

    public CreateCourseFragment() {
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


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
        if(course == null){
            course = new SuinoCourse("123123","Dani","somedomain.com/pic");
        }
        scrollView = (ScrollView) getView().findViewById(R.id.scrollView_createCourse);
        ll_default_map = (LinearLayout) getView().findViewById(R.id.ll_default_map);
        iv_map_circle = (RelativeLayout) getView().findViewById(R.id.rl_address);
        initCardViews();

        initMap();
    }

    private void initMap(){
        if(latLng != null){
            MapFormFragment mapFragment = new MapFormFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_map_form, mapFragment);
            fragmentTransaction.commit();
            mapFragment.updateCamera(latLng);
            cv_location.setBackgroundColor(Color.TRANSPARENT);
            ll_default_map.setVisibility(View.GONE);
            iv_map_circle.setVisibility(View.VISIBLE);

        }else{
            cv_location.setBackgroundColor(Color.WHITE);
            ll_default_map.setVisibility(View.VISIBLE);
            iv_map_circle.setVisibility(View.GONE);
        }

    }

    public void setLocation(LatLng latLng){
        if(latLng != null) {
            course.setLocation(latLng.longitude, latLng.latitude);
            this.latLng = latLng;
        }
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
        if(course.getCategory() != null && !course.getCategory().isEmpty()){
            tv_category.setText(course.getCategory());
            tv_category.setTextColor(getResources().getColor(R.color.textColorPrimaryDark));
        }
        cv_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDialog dialog = new MaterialDialog.Builder(activity)
                        .title("Choose Category")
                        .items(R.array.category_list)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                course.setCategory(text.toString());
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
        cv_increase = (CardView) getView().findViewById(R.id.cv_increase);
        cv_decrease = (CardView) getView().findViewById(R.id.cv_decrease);
        tv_groupSize = (TextView) getView().findViewById(R.id.tv_groupSize);
        if(course.getGroupSize() != 0){
            tv_groupSize.setText(course.getGroupSize() + " Curious Minds");
        }else{
            course.setGroupSize(GROUP_SIZE_DEFAULT);
            tv_groupSize.setText(GROUP_SIZE_DEFAULT + " Curious Minds");
        }


        cv_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int groupSize = course.getGroupSize() - 1;
                if (groupSize < 1) {
                    return;
                }

                course.setGroupSize(groupSize);
                tv_groupSize.setText(groupSize + " Curious Minds");

            }
        });

        cv_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int groupSize = course.getGroupSize() + 1;
                if(groupSize > GROUP_SIZE_MAX){
                    return;
                }
                course.setGroupSize(groupSize);
                tv_groupSize.setText(groupSize + " Curious Minds");
            }
        });

    }

    private void initLevelCardView(){

    }

    private void initDescriptionCardView(){

        et_description = (EditText) getView().findViewById(R.id.et_description);
        cv_description = (CardView) getView().findViewById(R.id.cv_description);
        cv_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_description.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_description, InputMethodManager.SHOW_IMPLICIT);
                scrollView.scrollTo(0, cv_description.getTop());
            }
        });
        et_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > DESCRIPTION_MAX){
                    et_description.setText(course.getDescription());
                    return;
                }
                course.setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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



