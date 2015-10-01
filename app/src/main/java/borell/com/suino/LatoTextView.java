package borell.com.suino;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by daniellohse on 10/1/15.
 */
public class LatoTextView  extends TextView {

    public LatoTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public LatoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public LatoTextView(Context context) {
        super(context);
        init(null);
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
