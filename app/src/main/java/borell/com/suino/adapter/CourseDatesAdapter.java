package borell.com.suino.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import borell.com.suino.R;
import borell.com.suino.model.SuinoEvent;


public class CourseDatesAdapter extends RecyclerView.Adapter<CourseDatesAdapter.MyViewHolder>  {
    private LayoutInflater inflater;
    ArrayList<SuinoEvent> dates;
    private FragmentActivity context;


    public CourseDatesAdapter(FragmentActivity activity, ArrayList<SuinoEvent> dates) {
        this.context = activity;
        this.dates = dates;
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
        holder.tv_day.setText("Test");
        holder.tv_freq.setText("Weekly");

    }

    @Override
    public int getItemCount() {
            return dates.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_day;
        TextView tv_freq;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_day = (TextView) itemView.findViewById(R.id.tv_day_course_list);
            tv_freq = (TextView) itemView.findViewById(R.id.tv_freq_course_list);

        }
    }
}
