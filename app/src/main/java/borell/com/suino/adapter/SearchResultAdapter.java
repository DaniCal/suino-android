package borell.com.suino.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import borell.com.suino.R;
import borell.com.suino.model.SuinoEvent;
import borell.com.suino.model.SuinoResultItem;
import borell.com.suino.view.LatoTextView;


public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.MyViewHolder>  {
    private LayoutInflater inflater;
    ArrayList<SuinoResultItem> results;
//    private FragmentActivity context;

    public SearchResultAdapter(Activity context, ArrayList<SuinoResultItem> results) {
//        this.context = activity;
        this.results = results;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_search_result, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        Information current = data.get(position);
//        holder.text.setText(current.title);

//        holder.tv_day.setText("Test");
//        holder.tv_freq.setText("Weekly");

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LatoTextView tv_keyword;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_keyword = (LatoTextView) itemView.findViewById(R.id.tv_search_result_time_hint);

        }
    }

}
