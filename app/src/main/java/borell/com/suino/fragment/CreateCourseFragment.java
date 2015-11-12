package borell.com.suino.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
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
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.google.android.gms.maps.model.LatLng;
import com.wefika.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.Calendar;

import borell.com.suino.model.SuinoEvent;
import borell.com.suino.view.createCourseView.LabelLinearLayout;
import borell.com.suino.R;
import borell.com.suino.activity.CreateCourseInterface;
import borell.com.suino.model.SuinoCourse;


public class CreateCourseFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {

    private SuinoCourse course;

    private CreateCourseInterface mCallback;
    private Activity activity;
    private ScrollView scrollView;
    private TextView tv_category;
    private TextView tv_price;
    private CardView cv_location;
    private ImageView iv_newbie;
    private ImageView iv_beginner;
    private ImageView iv_advanced;
    private CardView cv_description;
    private TextView tv_groupSize;
    private EditText et_description;
    private LinearLayout ll_default_map;
    private RelativeLayout iv_map_circle;
    private FlowLayout fl_tag;
    private TextView tv_keyword;
    private final int PRICE_MAX = 50;
    private final int PRICE_DEFAULT = 15;
    private final int GROUP_SIZE_MAX = 20;
    private final int GROUP_SIZE_DEFAULT= 2;
    private final int DESCRIPTION_MAX = 200;

    private ArrayList<LabelLinearLayout> labels = new ArrayList<>();

    CourseDatesFragment courseDatesFragment;

    LatLng latLng;

    public CreateCourseFragment() {
        if(course == null){
            course = new SuinoCourse();
        }
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

        scrollView = (ScrollView) getView().findViewById(R.id.scrollView_createCourse);
        ll_default_map = (LinearLayout) getView().findViewById(R.id.ll_default_map);
        iv_map_circle = (RelativeLayout) getView().findViewById(R.id.rl_address);
        fl_tag  = (FlowLayout) getView().findViewById(R.id.fl_tags);
        fl_tag.removeAllViews();

        initCourseDatesFragment();
        initCardViews();
        initMap();
    }


    public void initCourseDatesFragment(){
        courseDatesFragment = new CourseDatesFragment();

        if(course != null && course.getEvents() != null){
            courseDatesFragment.updateList(course.getEvents());
        }

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_course_dates, courseDatesFragment);
        fragmentTransaction.commit();



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
        CardView cv_category = (CardView) getView().findViewById(R.id.cv_category);
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

    public void deleteTag(int position){
        fl_tag.removeViewAt(position);
        labels.remove(position);
        course.removeKeyword(position);
        for(int i = position; i < labels.size(); i++){
            LabelLinearLayout item = labels.get(i);
            item.decreasePosition();
        }

        if(course.getKeywords().size() == 0){
            tv_keyword.setVisibility(View.VISIBLE);
        }
    }

    private void initTagCardView(){
        CardView cv_tag = (CardView) getView().findViewById(R.id.cv_tag);
        final FlowLayout fl_tag  = (FlowLayout) getView().findViewById(R.id.fl_tags);
        tv_keyword = (TextView) getView().findViewById(R.id.tv_keyword);
        cv_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new MaterialDialog.Builder(activity)
                        .inputRangeRes(2, 20, R.color.textColorPrimaryDark)
                        .title("Add a Keyword (Volleyball, Techniques...)")
                        .input(null, null, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                addLabel(input.toString());
                                tv_keyword.setVisibility(View.GONE);
                            }
                        }).show();
            }
        });
        for(LabelLinearLayout item : labels){
            fl_tag.addView(item);
            tv_keyword.setVisibility(View.GONE);
        }
    }

    private void addLabel(String tag){
        LabelLinearLayout labelView = new LabelLinearLayout(getActivity(), tag, fl_tag.getChildCount());
        labels.add(labelView);
        fl_tag.addView(labelView);
        course.addKeyword(tag);
    }

    private void initPriceCardView(){
        tv_price = (TextView) getView().findViewById(R.id.tv_price);
        SeekBar sb_price = (SeekBar) getView().findViewById(R.id.sb_filter_distance);
        sb_price.setMax(PRICE_MAX);
        sb_price.setProgress(PRICE_DEFAULT);
        course.setPrice(PRICE_DEFAULT);
        sb_price.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_price.setText(seekBar.getProgress() + " €/h");
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
        CardView cv_increase = (CardView) getView().findViewById(R.id.cv_increase);
        CardView cv_decrease = (CardView) getView().findViewById(R.id.cv_decrease);
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
                if (groupSize > GROUP_SIZE_MAX) {
                    return;
                }
                course.setGroupSize(groupSize);
                tv_groupSize.setText(groupSize + " Curious Minds");
            }
        });

    }

    private void initLevelCardView(){
        CardView cv_newbie = (CardView) getView().findViewById(R.id.cv_filter_newbie);
        iv_newbie =(ImageView) getView().findViewById(R.id.iv_filter_newbie);
        CardView cv_beginner = (CardView) getView().findViewById(R.id.cv_filter_beginner);
        iv_beginner =(ImageView) getView().findViewById(R.id.iv_filter_beginner);
        CardView cv_advanced = (CardView) getView().findViewById(R.id.cv_filter_advanced);
        iv_advanced = (ImageView) getView().findViewById(R.id.iv_filter_advanced);
        cv_newbie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setLevel(1);
            }
        });
        cv_beginner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setLevel(2);
            }
        });
        cv_advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLevel(3);
            }
        });

        setLevel(course.getLevel());


    }

    private void setLevel(int level){
        switch(level){
            case 1:
                course.setLevel(level);
                selectNewbie();
                break;
            case 2:
                course.setLevel(level);
                selectBeginner();
                break;
            case 3:
                course.setLevel(level);
                selectAdvanced();
                break;
        }
    }

    private void selectNewbie(){
        Drawable newbieSelected = getResources().getDrawable(R.drawable.icon_newbie_on_level );
        Drawable beginnerUnselected = getResources().getDrawable(R.drawable.icon_beginner_off_level );
        Drawable advancedUnselected = getResources().getDrawable(R.drawable.icon_advanced_off_level );
        iv_newbie.setImageDrawable(newbieSelected);
        iv_beginner.setImageDrawable(beginnerUnselected);
        iv_advanced.setImageDrawable(advancedUnselected);
    }

    private void selectBeginner(){
        Drawable newbieUnselected = getResources().getDrawable(R.drawable.icon_newbie_off_level );
        Drawable beginnerSelected = getResources().getDrawable(R.drawable.icon_beginner_on_level );
        Drawable advancedUnselected = getResources().getDrawable(R.drawable.icon_advanced_off_level );
        iv_newbie.setImageDrawable(newbieUnselected);
        iv_beginner.setImageDrawable(beginnerSelected);
        iv_advanced.setImageDrawable(advancedUnselected);
    }

    private void selectAdvanced(){
        Drawable newbieUnselected = getResources().getDrawable(R.drawable.icon_newbie_off_level );
        Drawable beginnerUnselected = getResources().getDrawable(R.drawable.icon_beginner_off_level );
        Drawable advancedSelected = getResources().getDrawable(R.drawable.icon_advanced_on_level );
        iv_newbie.setImageDrawable(newbieUnselected);
        iv_beginner.setImageDrawable(beginnerUnselected);
        iv_advanced.setImageDrawable(advancedSelected);
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
                if (charSequence.length() > DESCRIPTION_MAX) {
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
                fl_tag.removeAllViews();
                mCallback.onShowMap();
            }
        });


    }

    private void initCourseDateCardView(){
        initCourseDatesFragment();
        CardView cv_add_date = (CardView) getView().findViewById(R.id.cv_add_date);

        cv_add_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPickDayDialog();
            }
        });
    }

    private void showPickDayDialog(){
        MaterialDialog dialog = new MaterialDialog.Builder(activity)
                .title("Choose Day of the Week")
                .positiveText("OK")
                .items(R.array.day_list)

                .widgetColorRes(R.color.colorPrimary)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        final Calendar start = Calendar.getInstance();
                        final Calendar end = Calendar.getInstance();

                        if (getDayOfTheWeekFromDialog(which) == -1) {
                            showPickDateDialog(start, end);
                            return true;
                        }
                        while (start.get(Calendar.DAY_OF_WEEK) != getDayOfTheWeekFromDialog(which)) {
                            start.add(Calendar.DATE, 1);
                            end.add(Calendar.DATE, 1);
                        }


                        showPickStartTimeDialog(start, end, false);

                        return true;
                    }
                })
                .show();
    }

    private void showPickDateDialog(final Calendar start, final Calendar end){
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                null,
                start.get(Calendar.YEAR),
                start.get(Calendar.MONTH),
                start.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
                start.set(Calendar.YEAR, year);
                start.set(Calendar.MONTH, monthOfYear);
                start.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                end.set(Calendar.YEAR, year);
                end.set(Calendar.MONTH, monthOfYear);
                end.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                showPickStartTimeDialog(start, end, true);

            }
        });
        dpd.show(activity.getFragmentManager(), "Datepickerdialog");
    }

    private void showPickStartTimeDialog(final Calendar start, final Calendar end, final boolean isDate){
        TimePickerDialog dpd = TimePickerDialog.newInstance(
                null,
                start.get(Calendar.HOUR_OF_DAY),
                0,
                true
        );

        dpd.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i1) {
                start.set(Calendar.HOUR_OF_DAY, i);
                start.set(Calendar.MINUTE, i1);
                showPickEndTimeDialog(start, end, isDate);
            }
        });
        dpd.setTitle("Select Start Time");
        dpd.show(activity.getFragmentManager(), "Datepickerdialog");
    }

    private void showPickEndTimeDialog(final Calendar start, final Calendar end, final boolean isDate){


        TimePickerDialog dpd2 = TimePickerDialog.newInstance(
                CreateCourseFragment.this,
                start.get(Calendar.HOUR_OF_DAY) + 1,
                0,
                true
        );

        dpd2.setTitle("Starting at " + start.get(Calendar.HOUR_OF_DAY) + ":" + start.get(Calendar.MINUTE));
        dpd2.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i1) {
                end.set(Calendar.HOUR_OF_DAY, i);
                end.set(Calendar.MINUTE, i1);
                if (isDate) {
                    SuinoEvent event = new SuinoEvent(start.getTimeInMillis(), end.getTimeInMillis());
                    course.addSuinoEvent(event);
                }
                courseDatesFragment.updateList(course.getEvents());
            }
        });
        dpd2.show(activity.getFragmentManager(), "Datepickerdialog");
    }

    private int getDayOfTheWeekFromDialog(int which){
        switch(which){
            case 0:
                return Calendar.MONDAY;
            case 1:
                return Calendar.TUESDAY;
            case 2:
                return Calendar.WEDNESDAY;
            case 3:
                return Calendar.THURSDAY;
            case 4:
                return Calendar.FRIDAY;
            case 5:
                return Calendar.SATURDAY;
            case 6:
                return Calendar.SUNDAY;
            default:
                return -1;


        }
    }


    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i1) {

        int a = i;
        int b = i1;

    }

    public SuinoCourse getCourse() {
        return course;
    }
}



