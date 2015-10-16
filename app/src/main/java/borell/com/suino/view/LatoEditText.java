package borell.com.suino.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import borell.com.suino.R;

/**
 * Created by daniellohse on 10/1/15.
 */
public class LatoEditText extends EditText {

    public LatoEditText(Context context) {
        super(context);
        init(null);
    }

    public LatoEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LatoEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }



    private void init(AttributeSet attrs) {
        if (attrs!=null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LatoTextView);
            String fontName = a.getString(R.styleable.LatoTextView_typefaceAsset);
            if (fontName!=null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), fontName);
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }
}