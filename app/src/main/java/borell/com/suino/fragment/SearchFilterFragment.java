package borell.com.suino.fragment;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.facebook.FacebookSdk;

import borell.com.suino.R;
import borell.com.suino.activity.SearchCourseInterface;
import borell.com.suino.model.SuinoFilter;


public class SearchFilterFragment extends Fragment {

    SuinoFilter filterOptions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        filterOptions = new SuinoFilter(2,2);
        return inflater.inflate(R.layout.fragment_search_filter, container, false);
    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initLevelFilter();
        initGroupFilter();
    }

    public SuinoFilter getFilterOption(){
        return this.filterOptions;
    }

    public void initCategoryFilter(){
        CardView cv_category = (CardView) getView().findViewById(R.id.cv_filter_category);

    }

    public void initLevelFilter(){
        CardView cv_newbie = (CardView) getView().findViewById(R.id.cv_filter_newbie);
        CardView cv_beginner = (CardView) getView().findViewById(R.id.cv_filter_beginner);
        CardView cv_advanced = (CardView) getView().findViewById(R.id.cv_filter_advanced);


        final ImageView iv_newbie = (ImageView) getView().findViewById(R.id.iv_filter_newbie);
        final ImageView iv_beginner = (ImageView) getView().findViewById(R.id.iv_filter_beginner);
        final ImageView iv_advanced = (ImageView) getView().findViewById(R.id.iv_filter_advanced);

        cv_newbie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filterOptions.isLevelSet(1)){
                    Drawable newbieUnselected = getResources().getDrawable(R.drawable.icon_level_newbie_grey_xxxhdpi );
                    iv_newbie.setImageDrawable(newbieUnselected);
                    filterOptions.removeLevelFilter(1);
                }else{
                    Drawable newbieSelected = getResources().getDrawable(R.drawable.icon_level_newbie_yellow_xxxhdpi );
                    iv_newbie.setImageDrawable(newbieSelected);
                    filterOptions.addLevelFilter(1);
                }
            }
        });


        cv_beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filterOptions.isLevelSet(2)){
                    Drawable newbieUnselected = getResources().getDrawable(R.drawable.icon_level_beginner_grey_xxxhdpi );
                    iv_beginner.setImageDrawable(newbieUnselected);
                    filterOptions.removeLevelFilter(2);
                }else{
                    Drawable newbieSelected = getResources().getDrawable(R.drawable.icon_level_beginner_yellow_xxxhdpi );
                    iv_beginner.setImageDrawable(newbieSelected);
                    filterOptions.addLevelFilter(2);
                }
            }
        });

        cv_advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filterOptions.isLevelSet(3)){
                    Drawable newbieUnselected = getResources().getDrawable(R.drawable.icon_level_advanced_grey_xxxhdpi );
                    iv_advanced.setImageDrawable(newbieUnselected);
                    filterOptions.removeLevelFilter(3);
                }else{
                    Drawable newbieSelected = getResources().getDrawable(R.drawable.icon_level_advanced_yellow_xxxhdpi );
                    iv_advanced.setImageDrawable(newbieSelected);
                    filterOptions.addLevelFilter(3);
                }
            }
        });

    }

    public void initGroupFilter(){
        CardView cv_single = (CardView) getView().findViewById(R.id.cv_filter_single);
        CardView cv_group = (CardView) getView().findViewById(R.id.cv_filter_group);

        final ImageView iv_single = (ImageView) getView().findViewById(R.id.iv_filter_single);
        final ImageView iv_group = (ImageView) getView().findViewById(R.id.iv_filter_group);

        cv_single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Drawable singleSelected = getResources().getDrawable(R.drawable.icon_group_single_yellow_xxxhdpi );
                iv_single.setImageDrawable(singleSelected);

                Drawable groupUnselected = getResources().getDrawable(R.drawable.icon_group_group_grey_xxxhdpi );
                iv_group.setImageDrawable(groupUnselected);

                filterOptions.setGroupFilter(1);
            }
        });


        cv_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Drawable singleUnselected = getResources().getDrawable(R.drawable.icon_group_single_grey_xxxhdpi );
                iv_single.setImageDrawable(singleUnselected);

                Drawable groupSelected = getResources().getDrawable(R.drawable.icon_group_group_yellow_xxxhdpi );
                iv_group.setImageDrawable(groupSelected);

                filterOptions.setGroupFilter(2);
            }
        });
    }

    public void initMaxDistanceFilter(){
        SeekBar sb_maxDistance = (SeekBar) getView().findViewById(R.id.sb_filter_distance);

    }


}
