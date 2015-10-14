package borell.com.suino;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wefika.flowlayout.FlowLayout;

import borell.com.suino.activity.CreateCourseInterface;
import borell.com.suino.activity.UIInterface;


public class LabelLinearLayout extends LinearLayout {

    LayoutInflater inflater;
    private CreateCourseInterface mCallback;
    protected int position;

    public LabelLinearLayout(Context context, String tag, final int pos) {
        super(context);
        this.position = pos;
        inflater = LayoutInflater.from(context);
        CardView item = (CardView) inflater.inflate(R.layout.item_tag, this, false);
        TextView tv_label = (TextView) item.findViewById(R.id.tv_label);
        tv_label.setText(tag);
        FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        this.setLayoutParams(layoutParams);
        this.addView(item);
        mCallback = (CreateCourseInterface) context;


        item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onDeleteTag(position);
            }
        });

    }

    public void decreasePosition(){
        this.position--;
    }


}
