package borell.com.suino.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import borell.com.suino.R;
import borell.com.suino.model.SuinoCourse;


public class CourseDatesAdapter extends RecyclerView.Adapter<CourseDatesAdapter.MyViewHolder>  {
    private LayoutInflater inflater;
    ArrayList<SuinoCourse.CourseDate> dates;
    ArrayList<SuinoCourse.CourseDay> days;
    private FragmentActivity context;


    public CourseDatesAdapter(FragmentActivity activity, ArrayList<SuinoCourse.CourseDate> dates, ArrayList<SuinoCourse.CourseDay> days) {
        this.context = activity;
        this.dates = dates;
        this.days = days;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_course_day_dates, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
