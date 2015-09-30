package borell.com.suino.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import borell.com.suino.R;
import borell.com.suino.activity.CreateCourseInterface;
import borell.com.suino.activity.UIInterface;


public class CreateCourseFragment extends Fragment {

    private CreateCourseInterface mCallback;
    private Activity activity;
    private CardView cv_category;
    private CardView cv_location;

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

        cv_location =(CardView) getView().findViewById(R.id.cv_location);
        cv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.showMap();
            }
        });

        cv_category = (CardView) getView().findViewById(R.id.cv_category);
        cv_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDialog dialog = new MaterialDialog.Builder(activity)
                        .title("Choose Category")
                        .items(R.array.category_list)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                dialog.hide();
                                return true;
                            }
                        })
                        .show();
            }
        });


    }
}
